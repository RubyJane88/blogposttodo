package com.blogposttodo.blogposttodo;

import com.blogposttodo.blogposttodo.album.entity.AlbumEntity;
import com.blogposttodo.blogposttodo.album.repository.AlbumRepository;
import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.CompanyEntity;
import com.blogposttodo.blogposttodo.user.entity.GeoEntity;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlbumDataLoader implements CommandLineRunner {

    @Autowired
    AlbumRepository albumRepository;


    @Override
    public void run(String... args) throws Exception {
        loadAlbumData();
    }

    private void loadAlbumData()  {

     if (albumRepository.count() == 0) {


         var geo = new GeoEntity(UUID.randomUUID(), "37.762", "-122.446");
         var address = new AddressEntity(UUID.randomUUID(), "Kulas1", "Apt 556", "Gwenborough", "92998-3874", "NY", geo);
         var company = new CompanyEntity(UUID.randomUUID(), "Demagoguery-Cron2", "Multi-layered client-server neural-net2", "1-660-555-8808");
         var user = new UserEntity(UUID.randomUUID(), "Leane", "Bret", "since@gmail.com", address, company, "123456789", "yahoo.com");

         var album1 = new AlbumEntity(UUID.randomUUID(), "Piece Of Me", user);
         var album2 = new AlbumEntity(UUID.randomUUID(), "All Things Considered", user);
         var album3 = new AlbumEntity(UUID.randomUUID(), "Something's Gotta Give", user);
         var album4 = new AlbumEntity(UUID.randomUUID(), "Makes Me Wonder", user);
         var album5 = new AlbumEntity(UUID.randomUUID(), "The Long And Winding Road", user);
        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);
        albumRepository.save(album4);
        albumRepository.save(album5);


     }
        System.out.println("Loaded " + albumRepository.count());
    }



}
