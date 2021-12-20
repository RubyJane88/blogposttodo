package com.blogposttodo.blogposttodo.config.todo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class TodoDto {

    private UUID id;
    @NotBlank(message = "Title is required")
    private String title;
    private Boolean completed;


}
