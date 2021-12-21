package com.blogposttodo.blogposttodo.album.entity;


import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@ToString
/*the above decorators are equivalent of this @Data,
 and they don't slow down JPA*/
//@Data
@Table(name = "albums")
@Entity(name = "album")
@AllArgsConstructor
@NoArgsConstructor
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotNull(message = "Album name cannot be null")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @NotNull
    private UserEntity user;


}
