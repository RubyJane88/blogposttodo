package com.blogposttodo.blogposttodo.comment.contract;

import com.blogposttodo.blogposttodo.comment.entity.CommentEntity;

import java.util.UUID;

public interface CommentContract {

    Iterable<CommentEntity> findAllComments();

    CommentEntity findCommentById(UUID id);

    void deleteComment(UUID id);


    CommentEntity createComment(CommentEntity comment);

    void updateComment(UUID id, CommentEntity comment);



}
