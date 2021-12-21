package com.blogposttodo.blogposttodo.comment.entity;

import com.blogposttodo.blogposttodo.post.entities.PostEntity;
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
@Table(name = "comments")
@Entity(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "email is mandatory")
    private String email;

    private String body;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private PostEntity postEntity;



}
