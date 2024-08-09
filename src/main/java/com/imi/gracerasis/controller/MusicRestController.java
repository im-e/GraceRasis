package com.imi.gracerasis.controller;

import com.imi.gracerasis.model.entity.Chart;
import com.imi.gracerasis.model.entity.Music;
import com.imi.gracerasis.model.repository.ChartRepository;
import com.imi.gracerasis.model.repository.MusicRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MusicRestController {

    private final MusicRepository musicRepository;
    private final ChartRepository chartRepository;

    public MusicRestController(MusicRepository musicRepository, ChartRepository chartRepository) {
        this.musicRepository = musicRepository;
        this.chartRepository = chartRepository;
    }

//    @GetMapping("/chart/{level}")
//    public List<Chart> getChartByLevel(@PathVariable int level) {
//        return chartRepository.findChartByLevel(level);
//    }

    @GetMapping("/music")
    public List<Music> getMusicFromSearch(@RequestParam(required = false) String title,
                                          @RequestParam(required = false) String artist) {
        if (StringUtils.isBlank(title) && StringUtils.isBlank(artist)) {
            return musicRepository.findAll();
        }

        if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(artist)) {
            return musicRepository.findByTitleContainingIgnoreCaseAndArtistContainingIgnoreCase(title, artist);
        }

        if (StringUtils.isNotBlank(title)) {
            return musicRepository.findByTitleContainingIgnoreCase(title);
        }

        return musicRepository.findByArtistContainingIgnoreCase(artist);
    }

    @GetMapping("/music/all")
    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    }

    @GetMapping("/music/{id}")
    public Music getMusicById(@PathVariable int id) {
        return musicRepository.getMusicById(id);
    }



}
