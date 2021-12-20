package com.blogposttodo.blogposttodo.photo.contract;

import com.blogposttodo.blogposttodo.photo.entity.PhotoEntity;

import java.util.UUID;

public interface PhotoContract {
    Iterable<PhotoEntity> getAllPhotos();

    PhotoEntity findPhotoById(UUID id);

    void deletePhoto(UUID id);

    PhotoEntity createPhoto(PhotoEntity photo);

    void updatePhoto(UUID id, PhotoEntity photo);

}
