package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.MusicDifficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicDifficultyRepository extends JpaRepository<MusicDifficulty, Integer> {
}