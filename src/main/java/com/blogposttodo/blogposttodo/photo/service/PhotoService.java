package com.blogposttodo.blogposttodo.photo.service;

import com.blogposttodo.blogposttodo.exception.NotFoundException;
import com.blogposttodo.blogposttodo.photo.contract.PhotoContract;
import com.blogposttodo.blogposttodo.photo.entity.PhotoEntity;
import com.blogposttodo.blogposttodo.photo.repository.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class PhotoService implements PhotoContract {

    private final PhotoRepository photoRepository;


    @Override
    public Iterable<PhotoEntity> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public PhotoEntity findPhotoById(UUID id) {
        return findOrThrow(id);
    }

    @Override
    public void deletePhoto(UUID id) {
        photoRepository.deleteById(id);
    }

    @Override
    public PhotoEntity createPhoto(PhotoEntity photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void updatePhoto(UUID id, PhotoEntity photo) {
        findOrThrow(id);
        photoRepository.save(photo);

    }

    private PhotoEntity findOrThrow(final UUID id) {
        return photoRepository.findById(id).orElseThrow(() -> new NotFoundException("Photu by id " + id + " not found"));
    }
}
