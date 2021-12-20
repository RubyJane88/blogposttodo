package com.blogposttodo.blogposttodo.album.contract;

import com.blogposttodo.blogposttodo.album.entity.AlbumEntity;

import java.util.UUID;

public interface AlbumContract {
    Iterable<AlbumEntity> findAllAlbum();

    AlbumEntity findAlbumById(UUID id);

    AlbumEntity createAlbum(AlbumEntity albumEntity);

    void deleteAlbum(UUID id);

    void updateAlbum(UUID id, AlbumEntity albumEntity);

}
