package com.blogposttodo.blogposttodo.post.controller;


import com.blogposttodo.blogposttodo.post.dtos.PostDto;
import com.blogposttodo.blogposttodo.post.entities.PostEntity;
import com.blogposttodo.blogposttodo.post.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/posts")
//@PreAuthorize("isAuthenticated()") // for authorization
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;


    @GetMapping
    public List<PostDto> getAllPosts() {
//        log.info("getAllPosts()");
//        return modelMapper.map(postService.getAllPosts(), List.class);

        var postList = StreamSupport.stream(postService.getAllPosts().spliterator(), false).toList();

        return postList.stream().map(this::convertToDto).collect(java.util.stream.Collectors.toList());

    }

    @GetMapping("/{id}")
    public PostDto getPostById(UUID id) {
        return convertToDto(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable("id") UUID id) {
        postService.deletePostById(id);
    }

    @PostMapping
    public PostDto createPost (@Valid @RequestBody PostDto postDto) {
        var postEntity = convertToEntity(postDto);
        return convertToDto(postService.createPost(postEntity));
    }

    @PutMapping("/{id}")
    public void putPost (@PathVariable("id") UUID id, @Valid @RequestBody PostDto postDto) {
       if(!id.equals(postDto.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id in path and id in body must match");

       var postEntity = convertToEntity(postDto);
       postService.updatePost(id, postEntity);
    }


    private PostDto convertToDto(PostEntity postEntity) {
        return modelMapper.map(postEntity, PostDto.class);
    }

    private PostEntity convertToEntity(PostDto postDto) {
        return modelMapper.map(postDto, PostEntity.class);
    }

}
