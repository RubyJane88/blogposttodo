package com.blogposttodo.blogposttodo.album.controller;


import com.blogposttodo.blogposttodo.album.dto.AlbumDto;
import com.blogposttodo.blogposttodo.album.entity.AlbumEntity;
import com.blogposttodo.blogposttodo.album.service.AlbumService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/albums")
public class AlbumController {

    private AlbumService albumService;
    private ModelMapper modelMapper;

    @GetMapping
    public List<AlbumDto> getAllAlbums() {
        var albumList = StreamSupport.stream(albumService.findAllAlbum().spliterator(), false).toList();

        return albumList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AlbumDto getAlbumById(@PathVariable("id")UUID id) {
        return convertToDto(albumService.findAlbumById(id));
    }

   @DeleteMapping("/{id}")
    public void deleteAlbumById(@PathVariable("id")UUID id) {
        albumService.deleteAlbum(id);
   }

   @PostMapping
    public AlbumDto createAlbum(@RequestBody AlbumDto albumDto) {
        var albumEntity = convertToEntity(albumDto);

        return convertToDto(albumService.createAlbum(albumEntity));
    }

    @PutMapping("/{id}")
    public void putAlbum (@PathVariable("id")UUID id, @RequestBody AlbumDto albumDto) {

        if (!id.equals(albumDto.getId()))  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id in path and body must match");

        var albumEntity = convertToEntity(albumDto);
        albumService.updateAlbum(id, albumEntity);
    }


    private AlbumDto convertToDto(AlbumEntity albumEntity) {

        return modelMapper.map(albumEntity, AlbumDto.class);
    }

    private AlbumEntity convertToEntity(AlbumDto albumDto) {
        return modelMapper.map(albumDto, AlbumEntity.class);
    }

}
