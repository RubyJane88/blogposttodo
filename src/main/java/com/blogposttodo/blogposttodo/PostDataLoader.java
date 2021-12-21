package com.blogposttodo.blogposttodo;

import com.blogposttodo.blogposttodo.post.entities.PostEntity;
import com.blogposttodo.blogposttodo.post.repository.PostRepository;
import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.CompanyEntity;
import com.blogposttodo.blogposttodo.user.entity.GeoEntity;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostDataLoader implements CommandLineRunner {


    private final PostRepository postRepository;

    public PostDataLoader(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPostData();
    }

    private void loadPostData() {
        if (postRepository.count() == 0) {

            var geo3 = new GeoEntity(UUID.randomUUID(), "39.762", "-122.446");
            var address = new AddressEntity(UUID.randomUUID(), "Kulasa", "Apt 556", "Gwen borough", "92998-3874", "NY", geo3);
            var company = new CompanyEntity(UUID.randomUUID(), "Demagoguery-Cron2", "Multi-layered client-server neural-net2", "1-660-555-8808");

            var user = new UserEntity(UUID.randomUUID(), "Leane", "Bret", "since@gmail.com", address, company, "123456789", "yahoo.com");


            var post1 = new PostEntity(UUID.randomUUID(), "First Post", "This is the first post", user);
            var post2 = new PostEntity(UUID.randomUUID(), "Second Post", "This is the second post", user);
            var post3 = new PostEntity(UUID.randomUUID(), "Third Post", "This is the third post", user);
            var post4 = new PostEntity(UUID.randomUUID(), "Fourth Post", "This is the fourth post", user);
            var post5 = new PostEntity(UUID.randomUUID(), "Fifth Post", "This is the fifth post", user);

            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            postRepository.save(post4);
            postRepository.save(post5);

        }

        System.out.println("Loaded Post Data");
    }
}