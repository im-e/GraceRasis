package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Track;
import com.imi.gracerasis.model.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

@Service
public class TrackPopulationService {

    private final TrackRepository trackRepository;
    private final NearNoahService nearNoahService;
    private final TrackImageService trackImageService;

    @Autowired
    public TrackPopulationService(TrackRepository trackRepository,
                                  NearNoahService nearNoahService,
                                  TrackImageService trackImageService) {
        this.trackRepository = trackRepository;
        this.trackImageService = trackImageService;
        this.nearNoahService = nearNoahService;
    }

    public void populateDatabase() throws IOException {
        List<Track> tracks = nearNoahService.getTrackData();
        for(Track track : tracks)
        {
            trackImageService.processTrack(track);
        }
        trackRepository.saveAll(tracks);
    }

}