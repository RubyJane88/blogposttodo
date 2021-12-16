package com.blogposttodo.blogposttodo.user.service;

import com.blogposttodo.blogposttodo.user.contract.UserContract;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.blogposttodo.blogposttodo.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class UserService implements UserContract {

    private final UserRepository repo;

    @Override
    public Iterable<UserEntity> findAllUser() {
        return repo.findAll();
    }

    public Iterable<UserEntity> findAllFlattenUser() {
        return repo.findAll();
    }

    @Override
    public UserEntity findUserById(UUID id) {
        return findOrThrow(id);
    }

    private UserEntity findOrThrow(final UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User by id" + id + "not found"));
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return repo.save(user);
    }

    @Override
    public void updateUser(UUID id, UserEntity user) {
        findOrThrow(id);
        repo.save(user);

    }

    @Override
    public void deleteUser(UUID id) {
        repo.deleteById(id);
    }
}
