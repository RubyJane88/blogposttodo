package com.blogposttodo.blogposttodo;


import com.blogposttodo.blogposttodo.album.entity.AlbumEntity;
import com.blogposttodo.blogposttodo.photo.entity.PhotoEntity;
import com.blogposttodo.blogposttodo.photo.repository.PhotoRepository;
import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.CompanyEntity;
import com.blogposttodo.blogposttodo.user.entity.GeoEntity;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PhotoDataLoader implements CommandLineRunner {

    final PhotoRepository photoRepository;

    public PhotoDataLoader(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPhotoData();

    }

    private void loadPhotoData() {
        if (photoRepository.count() == 0) {


            var geo2 = new GeoEntity(UUID.randomUUID(), "37.762", "-122.446");
            var address = new AddressEntity(UUID.randomUUID(), "Kulasa", "Apt 556", "Gwen borough", "92998-3874", "NY", geo2);
            var company = new CompanyEntity(UUID.randomUUID(), "Demagoguery-Cron2", "Multi-layered client-server neural-net2", "1-660-555-8808");

            var user = new UserEntity(UUID.randomUUID(), "Leane", "Bret", "since@gmail.com", address, company, "123456789", "yahoo.com");
            var album = new AlbumEntity(UUID.randomUUID(), "Album 1", user);

            var photo1 = new PhotoEntity(UUID.randomUUID(), "My first photo", "https://www.google.com", "This is my first photo", album);
            var photo2 = new PhotoEntity(UUID.randomUUID(), "My second photo", "https://www.google.com", "This is my second photo", album);
            var photo3 = new PhotoEntity(UUID.randomUUID(), "My third photo", "https://www.google.com", "This is my third photo", album);
            var photo4 = new PhotoEntity(UUID.randomUUID(), "My fourth photo", "https://www.google.com", "This is my fourth photo", album);
            var photo5 = new PhotoEntity(UUID.randomUUID(), "My fifth photo", "https://www.google.com", "This is my fifth photo", album);

            photoRepository.save(photo1);
            photoRepository.save(photo2);
            photoRepository.save(photo3);
            photoRepository.save(photo4);
            photoRepository.save(photo5);

        }

        System.out.println("Loaded " + photoRepository.count() + " photos");
    }

}
