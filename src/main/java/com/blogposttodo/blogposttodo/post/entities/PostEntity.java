package com.blogposttodo.blogposttodo.post.entities;

import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@ToString
/*the above decorators are equivalent of this @Data,
 and they don't slow down JPA*/
//@Data
@Table(name = "posts")
@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    @NotNull
    private UserEntity user;


    /*These 2 methods, equals and hashCode, below are required if @Data is not used*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PostEntity that = (PostEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
