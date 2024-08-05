package com.imi.gracerasis.controller;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.service.NearNoahService;
import com.imi.gracerasis.service.TachiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackRestController {

    private final NearNoahService nearNoahService;
    private final TachiService tachiService;

    public TrackRestController(NearNoahService nearNoahService, TachiService tachiService) {
        this.nearNoahService = nearNoahService;
        this.tachiService = tachiService;
    }

    @GetMapping("/track/{level}")
    public List<MusicXml> getTrackByLevel(@PathVariable String level) {
        return nearNoahService.getTrackData(level);
    }

    @GetMapping("/track/")
    public List<MusicXml> getTrackByArtist(@RequestParam String artist) {
        return nearNoahService.getTrackData(artist);
    }

    @GetMapping("/tracks")
    public List<MusicXml> getAllTracks() {
        return nearNoahService.getTrackData();
    }

}
