package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MusicRepository extends MongoRepository<Music, String> {

    List<Music> findByTitleContainingIgnoreCase(String titlePart);
    List<Music> findByArtistContainingIgnoreCase(String artistPart);
    List<Music> findByTitleContainingIgnoreCaseAndArtistContainingIgnoreCase(String title, String artist);
    List<Music> findByNoviceLevelOrAdvancedLevelOrExhaustLevelOrFinalLevel(int noviceLevel, int advancedLevel, int exhaustLevel, int finalLevel);
    Music getMusicById(int id);
}
