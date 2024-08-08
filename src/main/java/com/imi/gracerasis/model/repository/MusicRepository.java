package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MusicRepository extends MongoRepository<Music, String> {

    List<Music> findByTitle(String title);
    List<Music> findByArtist(String artist);
}
