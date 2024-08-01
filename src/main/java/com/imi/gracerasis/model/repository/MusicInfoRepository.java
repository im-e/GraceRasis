package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.MusicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicInfoRepository extends JpaRepository<MusicInfo, Integer> {
}