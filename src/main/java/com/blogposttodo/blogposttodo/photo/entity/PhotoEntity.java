package com.blogposttodo.blogposttodo.photo.entity;


import com.blogposttodo.blogposttodo.album.entity.AlbumEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private AlbumEntity albumEntity;


    /*These 2 methods, equals and hashCode, below are required if @Data is not used*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhotoEntity that = (PhotoEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
