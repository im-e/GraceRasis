package com.imi.gracerasis.controller;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.model.entity.Chart;
import com.imi.gracerasis.model.entity.Music;
import com.imi.gracerasis.model.repository.ChartRepository;
import com.imi.gracerasis.model.repository.MusicRepository;
import com.imi.gracerasis.service.NearNoahService;
import com.imi.gracerasis.service.TachiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChartRestController {

    private final MusicRepository musicRepository;
    private final ChartRepository chartRepository;

    public ChartRestController(MusicRepository musicRepository, ChartRepository chartRepository) {
        this.musicRepository = musicRepository;
        this.chartRepository = chartRepository;
    }

//    @GetMapping("/chart/{level}")
//    public List<Chart> getChartByLevel(@PathVariable int level) {
//        return chartRepository.findChartByLevel(level);
//    }

    @GetMapping("/chart/{musicId}")
    public List<Chart> getChartByMusicID(@PathVariable int musicId) {
        return chartRepository.findChartByMusicId(musicId);
    }

    @GetMapping("/charts")
    public List<Chart> getAllCharts() {
        return chartRepository.findAll();
    }

}
