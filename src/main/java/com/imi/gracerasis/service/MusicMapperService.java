package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Music;
import com.imi.gracerasis.model.entity.*;
import org.springframework.stereotype.Service;

@Service
public class MusicMapperService {

    public MusicEntity toEntity(Music dto) {
        MusicEntity entity = new MusicEntity();
        entity.setId(dto.getId());
        entity.setInfo(toMusicInfoEntity(dto.getInfo()));
        entity.setCharts(toChartsEntity(dto.getCharts()));
        return entity;
    }

    private MusicEntity.MusicInfoEntity toMusicInfoEntity(Music.MusicInfo dto) {
        MusicEntity.MusicInfoEntity entity = new MusicEntity.MusicInfoEntity();
        entity.setLabel(dto.getLabel());
        entity.setTitle(dto.getTitle());
        entity.setTitleYomigana(dto.getTitleYomigana());
        entity.setArtist(dto.getArtist());
        entity.setArtistYomigana(dto.getArtistYomigana());
        entity.setAscii(dto.getAscii());
        entity.setBpmMax(dto.getBpmMax());
        entity.setBpmMin(dto.getBpmMin());
        entity.setDistributionDate(dto.getDistributionDate());
        entity.setVolume(dto.getVolume());
        entity.setBgNo(dto.getBgNo());
        entity.setGenre(dto.getGenre());
        entity.setFixed(dto.isFixed());
        entity.setVersion(dto.getVersion());
        entity.setDemoPri(dto.getDemoPri());
        entity.setInfVer(dto.getInfVer());
        return entity;
    }

    private MusicEntity.ChartsEntity toChartsEntity(Music.Charts dto) {
        MusicEntity.ChartsEntity entity = new MusicEntity.ChartsEntity();
        if (dto.getNovice() != null) {
            entity.setNovice(toDifficultyEntity(dto.getNovice()));
        }
        if (dto.getAdvanced() != null) {
            entity.setAdvanced(toDifficultyEntity(dto.getAdvanced()));
        }
        if (dto.getExhaust() != null) {
            entity.setExhaust(toDifficultyEntity(dto.getExhaust()));
        }
        if (dto.getInfinite() != null) {
            entity.setInfinite(toDifficultyEntity(dto.getInfinite()));
        }
        return entity;
    }

    private MusicEntity.DifficultyEntity toDifficultyEntity(Music.Difficulty dto) {
        MusicEntity.DifficultyEntity entity = new MusicEntity.DifficultyEntity();
        entity.setLevel(dto.getLevel());
        entity.setIllustratedBy(dto.getIllustratedBy());
        entity.setEffectedBy(dto.getEffectedBy());
        entity.setPrice(dto.getPrice());
        entity.setLimited(dto.getLimited());
        entity.setJacketPrint(dto.getJacketPrint());
        entity.setJacketMask(dto.getJacketMask());
        entity.setMaxExscore(dto.getMaxExscore());
        if (dto.getRadar() != null) {
            entity.setRadar(toRadarEntity(dto.getRadar()));
        }
        return entity;
    }

    private MusicEntity.RadarEntity toRadarEntity(Music.Radar dto) {
        MusicEntity.RadarEntity entity = new MusicEntity.RadarEntity();
        entity.setNotes(dto.getNotes());
        entity.setPeak(dto.getPeak());
        entity.setTsumami(dto.getTsumami());
        entity.setTricky(dto.getTricky());
        entity.setHandTrip(dto.getHandTrip());
        entity.setOneHand(dto.getOneHand());
        return entity;
    }


}
