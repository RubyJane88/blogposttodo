package com.blogposttodo.blogposttodo.post.service;

import com.blogposttodo.blogposttodo.exception.NotFoundException;
import com.blogposttodo.blogposttodo.post.contract.PostContract;
import com.blogposttodo.blogposttodo.post.entities.PostEntity;
import com.blogposttodo.blogposttodo.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class PostService implements PostContract {

    private final PostRepository postRepository;

    @Override
    public Iterable<PostEntity> getAllPosts() {

        return postRepository.findAll();
    }

    @Override
    public PostEntity getPostById(UUID id) {
        return findOrThrow(id);
    }

    @Override
    public void deletePostById(UUID id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostEntity createPost(PostEntity photo) {
        return postRepository.save(photo);
    }

    @Override
    public void updatePost(UUID id, PostEntity photo) {
        findOrThrow(id);
        postRepository.save(photo);

    }

    private PostEntity findOrThrow(final UUID id) {
        return postRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Post by id" + id + "not found"));
    }
}
