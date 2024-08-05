package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicRepository extends MongoRepository<Music, String> {


}
