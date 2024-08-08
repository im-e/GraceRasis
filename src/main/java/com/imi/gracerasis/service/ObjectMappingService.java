package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.model.entity.Chart;
import com.imi.gracerasis.model.entity.Music;
import org.springframework.stereotype.Service;

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

        return music;
    }

    public Chart toChartObject(MusicXml.Difficulty dto, int musicId, String difficulty)
    {
        Chart chart = new Chart();

        chart.setMusicId(musicId);
        chart.setDifficulty(difficulty);

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
