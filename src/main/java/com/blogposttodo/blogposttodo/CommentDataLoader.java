package com.blogposttodo.blogposttodo;


import com.blogposttodo.blogposttodo.comment.entity.CommentEntity;
import com.blogposttodo.blogposttodo.comment.repository.CommentRepository;
import com.blogposttodo.blogposttodo.post.entities.PostEntity;
import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.CompanyEntity;
import com.blogposttodo.blogposttodo.user.entity.GeoEntity;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentDataLoader implements CommandLineRunner {

    private final CommentRepository commentRepository;

    public CommentDataLoader(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCommentData();

    }

    private void loadCommentData() {
        if(commentRepository.count() == 0) {

            var geo4 = new GeoEntity(UUID.randomUUID(), "39.742", "-122.446");
            var address = new AddressEntity(UUID.randomUUID(), "Kulasasi", "Apt 556", "Gwen borough", "92998-3874", "NY", geo4);
            var company = new CompanyEntity(UUID.randomUUID(), "Demagoguery-Cron2", "Multi-layered client-server neural-net2", "1-660-555-8808");

            var user = new UserEntity(UUID.randomUUID(), "Leane", "Bret", "since@gmail.com", address, company, "123456789", "yahoo.com");

            var post1 = new PostEntity(UUID.randomUUID(), "First Post", "This is the first post", user);

            var comment1 = new CommentEntity(UUID.randomUUID(), "This is the comment name", "arjaycee@yahoo.com",
                    "This is the comment body", post1);

            var comment2 = new CommentEntity(UUID.randomUUID(), "This is the comment name", "arjaycee@yahoo.com",
                    "This is the comment body", post1);

            var comment3 = new CommentEntity(UUID.randomUUID(), "This is the comment name", "arjaycee@yahoo.com",
                    "This is the comment body", post1);

            var comment4 = new CommentEntity(UUID.randomUUID(), "This is the comment name", "arjaycee@yahoo.com",
                    "This is the comment body", post1);

            var comment5 = new CommentEntity(UUID.randomUUID(), "This is the comment name", "arjaycee@yahoo.com",
                    "This is the comment body", post1);

            commentRepository.save(comment1);
            commentRepository.save(comment2);
            commentRepository.save(comment3);
            commentRepository.save(comment4);
            commentRepository.save(comment5);

        }

        System.out.println("Comment Data Loaded");
    }
}
