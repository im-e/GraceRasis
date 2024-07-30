package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Track;
import com.imi.gracerasis.model.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TrackPopulationService {

    private final TrackRepository trackRepository;
    private final NearNoahService nearNoahService;

    @Autowired
    public TrackPopulationService(TrackRepository trackRepository,
                                  NearNoahService nearNoahService) {
        this.trackRepository = trackRepository;
        this.nearNoahService = nearNoahService;
    }

    public void populateDatabase() {
        List<Track> tracks = nearNoahService.getTrackData();
        trackRepository.saveAll(tracks);
    }

}