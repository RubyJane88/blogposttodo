package com.blogposttodo.blogposttodo.comment.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class CommentDto {

    private UUID id;

    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "email is mandatory")
    private String email;
    private String body;

    private UUID postId;
}
