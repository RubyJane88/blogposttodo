package com.blogposttodo.blogposttodo.config.todo.dto;

import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class TodoDto {

    private UUID id;
    @NotBlank(message = "Title is required")
    private String title;
    private Boolean completed;
    private UUID userId;

}
