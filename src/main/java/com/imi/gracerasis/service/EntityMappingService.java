package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.MusicXml;
import com.imi.gracerasis.model.entity.Difficulty;
import com.imi.gracerasis.model.entity.Music;
import org.springframework.stereotype.Service;

@Service
public class EntityMappingService {

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

    public Difficulty toDifficultyObject(MusicXml.Difficulty dto)
    {
        Difficulty difficulty = new Difficulty();
        difficulty.setLevel(dto.getLevel());
        difficulty.setIllustratedBy(dto.getIllustratedBy());
        difficulty.setEffectedBy(dto.getEffectedBy());
        difficulty.setPrice(dto.getPrice());
        difficulty.setLimited(dto.getLimited());
        difficulty.setJacketPrint(dto.getJacketPrint());
        difficulty.setJacketMask(dto.getJacketMask());
        difficulty.setMaxExScore(dto.getMaxExscore());

        difficulty.setRadarNotes(dto.getRadar().getNotes());
        difficulty.setRadarPeak(dto.getRadar().getPeak());
        difficulty.setRadarTsumami(dto.getRadar().getTsumami());
        difficulty.setRadarTricky(dto.getRadar().getTricky());
        difficulty.setRadarHandTrip(dto.getRadar().getHandTrip());
        difficulty.setRadarOneHand(dto.getRadar().getOneHand());

        return difficulty;
    }




}
