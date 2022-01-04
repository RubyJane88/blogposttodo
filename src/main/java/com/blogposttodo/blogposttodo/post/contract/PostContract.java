package com.blogposttodo.blogposttodo.post.contract;

import com.blogposttodo.blogposttodo.post.entities.PostEntity;

import java.util.UUID;

public interface PostContract {

    Iterable<PostEntity> getAllPosts();

    PostEntity getPostById(UUID id);

    void deletePostById(UUID id);

    PostEntity createPost(PostEntity photo);

    void updatePost(UUID id, PostEntity photo);

}
