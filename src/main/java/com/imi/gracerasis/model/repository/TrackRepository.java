package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.DTO.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track, Integer> {

    Track findByID(Integer id);

}