package com.blogposttodo.blogposttodo.user.repository;

import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface UserRepository extends CrudRepository <UserEntity, UUID> {

}
