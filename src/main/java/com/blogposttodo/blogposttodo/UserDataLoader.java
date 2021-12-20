package com.blogposttodo.blogposttodo;

import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.CompanyEntity;
import com.blogposttodo.blogposttodo.user.entity.GeoEntity;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.blogposttodo.blogposttodo.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDataLoader implements CommandLineRunner {

    final UserRepository userRepository;

    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        //
        if (userRepository.count() == 0) {

            var geo = new GeoEntity(UUID.randomUUID(), "37.769", "-122.446");
            var address = new AddressEntity(UUID.randomUUID(),"Kulas", "Apt 556", "Gwenborough", "92998-3874", "NY", geo);
            var company = new CompanyEntity(UUID.randomUUID(), "Romaguera-Crona", "Multi-layered client-server neural-net", "1-660-555-8888");

            var user = new UserEntity(UUID.randomUUID(), "Leane", "Bret", "since@gmail.com", address, company, "123456789", "yahoo.com");
            userRepository.save(user);


        }
        System.out.println(userRepository.count());

    }

}

