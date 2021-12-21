package com.blogposttodo.blogposttodo.comment.service;

import com.blogposttodo.blogposttodo.comment.contract.CommentContract;
import com.blogposttodo.blogposttodo.comment.entity.CommentEntity;
import com.blogposttodo.blogposttodo.comment.repository.CommentRepository;
import com.blogposttodo.blogposttodo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CommentService implements CommentContract {

      private final CommentRepository commentRepository;

    @Override
    public Iterable<CommentEntity> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public CommentEntity findCommentById(UUID id) {
        return findOrThrow(id);
    }

    @Override
    public void deleteComment(UUID id) {
       commentRepository.deleteById(id);
    }

    @Override
    public CommentEntity createComment(CommentEntity comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void updateComment(UUID id, CommentEntity comment) {
        findOrThrow(id);
        commentRepository.save(comment);
    }

    private CommentEntity findOrThrow(final UUID id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
    }
}
