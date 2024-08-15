package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.model.entity.Chart;
import com.imi.gracerasis.model.entity.Music;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ObjectMappingService {

    public Music toMusicObject(MusicXml dto) {
        Music music = new Music();
        music.setId(dto.getId());
        music.setLabel(dto.getInfo().getLabel());
        music.setTitle(dto.getInfo().getTitle());
        music.setTitleYomigana(dto.getInfo().getTitleYomigana());
        music.setArtist(dto.getInfo().getArtist());
        music.setArtistYomigana(dto.getInfo().getArtistYomigana());
        music.setAscii(dto.getInfo().getAscii());
        music.setBpmMax(dto.getInfo().getBpmMax());
        music.setBpmMin(dto.getInfo().getBpmMin());
        music.setDistributionDate(dto.getInfo().getDistributionDate());
        music.setVolume(dto.getInfo().getVolume());
        music.setBgNo(dto.getInfo().getBgNo());
        music.setGenre(dto.getInfo().getGenre());
        music.setFixed(dto.getInfo().isFixed());
        music.setVersion(dto.getInfo().getVersion());
        music.setDemoPri(dto.getInfo().getDemoPri());
        music.setInfVer(dto.getInfo().getInfVer());

        music.setNoviceLevel(dto.getCharts().getNovice().getLevel());
        music.setAdvancedLevel(dto.getCharts().getAdvanced().getLevel());
        music.setExhaustLevel(dto.getCharts().getExhaust().getLevel());
        music.setFinalLevel(0);

        return music;
    }

    public Chart toChartObject(MusicXml.Difficulty dto, MusicXml music, String difficulty) {
        Chart chart = new Chart();

        chart.setMusicId(music.getId());

        if (Objects.equals(difficulty, "Infinite")) {

            switch (music.getInfo().getInfVer()) {
                case 1: chart.setDifficulty("Infinite");
                case 2: chart.setDifficulty("Infinite");
                case 3: chart.setDifficulty("Gravity");
                case 4: chart.setDifficulty("Heavenly");
                case 5: chart.setDifficulty("Vivid");
                case 6: chart.setDifficulty("Exceed");
                default: chart.setDifficulty("Infinite");
            }
        } else
        {
            chart.setDifficulty(difficulty);
        }

        chart.setTitle(music.getInfo().getTitle());
        chart.setArtist(music.getInfo().getArtist());
        chart.setLevel(dto.getLevel());
        chart.setIllustratedBy(dto.getIllustratedBy());
        chart.setEffectedBy(dto.getEffectedBy());
        chart.setPrice(dto.getPrice());
        chart.setLimited(dto.getLimited());
        chart.setJacketPrint(dto.getJacketPrint());
        chart.setJacketMask(dto.getJacketMask());
        chart.setMaxExScore(dto.getMaxExScore());

        chart.setRadarNotes(dto.getRadar().getNotes());
        chart.setRadarPeak(dto.getRadar().getPeak());
        chart.setRadarTsumami(dto.getRadar().getTsumami());
        chart.setRadarTricky(dto.getRadar().getTricky());
        chart.setRadarHandTrip(dto.getRadar().getHandTrip());
        chart.setRadarOneHand(dto.getRadar().getOneHand());

        return chart;
    }



}
