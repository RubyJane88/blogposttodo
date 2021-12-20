package com.blogposttodo.blogposttodo.album.service;

import com.blogposttodo.blogposttodo.album.contract.AlbumContract;
import com.blogposttodo.blogposttodo.album.entity.AlbumEntity;
import com.blogposttodo.blogposttodo.album.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AlbumService implements AlbumContract {

    private final AlbumRepository albumRepository;

    @Override
    public Iterable<AlbumEntity> findAllAlbum() {
        return albumRepository.findAll();
    }

    @Override
    public AlbumEntity findAlbumById(UUID id) {
        return  findOrThrow(id);
    }

    private AlbumEntity findOrThrow(final UUID id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Album by id" + id + "not found"));
    }

    @Override
    public AlbumEntity createAlbum(AlbumEntity albumEntity) {
        return albumRepository.save(albumEntity);
    }

    @Override
    public void deleteAlbum(UUID id) {
     albumRepository.deleteById(id);
    }

    @Override
    public void updateAlbum(UUID id, AlbumEntity albumEntity) {
      findOrThrow(id);
      albumRepository.save(albumEntity);
    }
}
