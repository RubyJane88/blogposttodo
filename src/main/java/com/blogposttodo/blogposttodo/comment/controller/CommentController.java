package com.blogposttodo.blogposttodo.comment.controller;


import com.blogposttodo.blogposttodo.comment.dto.CommentDto;
import com.blogposttodo.blogposttodo.comment.entity.CommentEntity;
import com.blogposttodo.blogposttodo.comment.service.CommentService;
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
@RequestMapping("api/v1/comments")
//@PreAuthorize("isAuthenticated()") // for authorization
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CommentDto> getAllComments() {

        var commentList = StreamSupport.stream(commentService.findAllComments().spliterator(), false).toList();

        return commentList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CommentDto getCommentById(@PathVariable("id") UUID id) {
        return convertToDto(commentService.findCommentById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") UUID id) {
        commentService.deleteComment(id);
    }

    @PostMapping
    public CommentDto createComment(@Valid @RequestBody CommentDto commentDto) {

        var commentEntity = convertToEntity(commentDto);
        return convertToDto(commentService.createComment(commentEntity));
    }

    @PutMapping("/{id}")
    public void putComment(@PathVariable("id") UUID id, @Valid @RequestBody CommentDto commentDto) {
       if(!id.equals(commentDto.getId())) throw new ResponseStatusException(
               HttpStatus.BAD_REQUEST, "Id in path and id in body must be the same");

    }


    private CommentDto convertToDto(CommentEntity commentEntity) {
        return modelMapper.map(commentEntity, CommentDto.class);
    }

    private CommentEntity convertToEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, CommentEntity.class);
    }



}
