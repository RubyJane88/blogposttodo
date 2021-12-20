package com.blogposttodo.blogposttodo.photo.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@ToString
/*the above decorators are equivalent of this @Data,
 and they don't slow down JPA*/
//@Data
@Table(name = "photos")
@Entity(name = "photo")
@AllArgsConstructor
@NoArgsConstructor
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "Please provide a title")
    private String title;

    private String url;
    private String thumbnailUrl;



}
