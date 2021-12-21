package com.blogposttodo.blogposttodo.post.repository;

import com.blogposttodo.blogposttodo.post.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<PostEntity, UUID> {
    // @Query("")
    // custom composite repository here

}