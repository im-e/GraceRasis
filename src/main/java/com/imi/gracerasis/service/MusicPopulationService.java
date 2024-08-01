package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Music;
import com.imi.gracerasis.model.repository.MusicRepository;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MusicPopulationService {

    private final MusicRepository musicRepository;
    private final NearNoahService nearNoahService;
    private final TrackImageService trackImageService;
    private final MusicDBService musicDBService;

    @Autowired
    public MusicPopulationService(MusicRepository musicRepository,
                                  NearNoahService nearNoahService,
                                  TrackImageService trackImageService, MusicDBService musicDBService) {
        this.musicRepository = musicRepository;
        this.trackImageService = trackImageService;
        this.nearNoahService = nearNoahService;
        this.musicDBService = musicDBService;
    }

    public void populateDatabase() {

        try {
            List<Music> musicList = musicDBService.getMusicDBData();
            for(Music music : musicList)
            {
                trackImageService.processTrack(music);
            }
            musicRepository.saveAll(musicList);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
