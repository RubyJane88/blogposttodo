package com.blogposttodo.blogposttodo.comment.repository;

import com.blogposttodo.blogposttodo.comment.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<CommentEntity, UUID> {
    // @Query("")
    // custom composite repository here

}