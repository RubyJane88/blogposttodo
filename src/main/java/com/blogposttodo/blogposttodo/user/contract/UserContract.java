package com.blogposttodo.blogposttodo.user.contract;

import com.blogposttodo.blogposttodo.user.entity.UserEntity;

import java.util.UUID;

public interface UserContract {
    Iterable<UserEntity> findAllUser();

    UserEntity findUserById(UUID id);

    UserEntity createUser(UserEntity user);

    void updateUser(UUID id, UserEntity user);

    void deleteUser(UUID id);


}
