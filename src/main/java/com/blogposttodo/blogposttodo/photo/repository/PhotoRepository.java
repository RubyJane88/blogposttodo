package com.blogposttodo.blogposttodo.photo.repository;

import com.blogposttodo.blogposttodo.photo.entity.PhotoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PhotoRepository extends CrudRepository<PhotoEntity, UUID> {
    // @Query("")
    // custom composite repository here

}
