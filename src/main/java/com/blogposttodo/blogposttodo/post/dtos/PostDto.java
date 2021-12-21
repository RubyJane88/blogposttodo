package com.blogposttodo.blogposttodo.post.dtos;


import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class PostDto {

    private UUID id;
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "body is required")
    private String body;

    private UUID userId;
}
