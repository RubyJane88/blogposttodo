package com.blogposttodo.blogposttodo.album.repository;


import com.blogposttodo.blogposttodo.album.entity.AlbumEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface AlbumRepository  extends CrudRepository<AlbumEntity, UUID> {

}
