package com.blogposttodo.blogposttodo.photo.controller;


import com.blogposttodo.blogposttodo.photo.dto.PhotoDto;
import com.blogposttodo.blogposttodo.photo.entity.PhotoEntity;
import com.blogposttodo.blogposttodo.photo.service.PhotoService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/photos")
//@PreAuthorize("isAuthenticated()") // for authorization
public class PhotoController {

    private final PhotoService photoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<PhotoDto> getAllPhotos() {

        var photoList = StreamSupport.stream(photoService.getAllPhotos().spliterator(), false).toList();

        return photoList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public PhotoDto getPhotoById(@PathVariable("id") UUID id) {
        return convertToDto(photoService.findPhotoById(id));

    }

    @DeleteMapping("/{id}")
    public void deletePhoto(@PathVariable("id") UUID id) {
        photoService.deletePhoto(id);
    }

    @PostMapping
    public PhotoDto createPhoto(@Valid @RequestBody PhotoDto photoDto) {
        var photoEntity = convertToEntity(photoDto);
        return convertToDto(photoService.createPhoto(photoEntity));
    }

    @PutMapping("/{id}")
    public void putPhoto(
            @PathVariable("id") UUID id,
            @Valid @RequestBody PhotoDto photoDto
    ) {
        if (!id.equals(photoDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        var photoEntity = convertToEntity(photoDto);
        photoService.updatePhoto(id, photoEntity);
    }


    private PhotoDto convertToDto(PhotoEntity photoEntity) {
        return modelMapper.map(photoEntity, PhotoDto.class);
    }

    private PhotoEntity convertToEntity(PhotoDto photoDto) {
        return modelMapper.map(photoDto, PhotoEntity.class);
    }

}

