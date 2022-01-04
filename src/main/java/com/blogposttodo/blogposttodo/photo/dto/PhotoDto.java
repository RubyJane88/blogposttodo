package com.blogposttodo.blogposttodo.photo.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class PhotoDto {
    private UUID id;

    @NotBlank(message = "Title is required")
    private String title;
    private String url;
    private String thumbnailUrl;

    private UUID albumId;


}
