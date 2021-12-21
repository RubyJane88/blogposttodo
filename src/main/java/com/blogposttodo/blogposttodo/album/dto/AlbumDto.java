package com.blogposttodo.blogposttodo.album.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class AlbumDto {

    private UUID id;
    @NotNull(message = "Title is required")
    private String title;

    private UUID userId;


}
