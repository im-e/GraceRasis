package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyRepository extends JpaRepository<Difficulty, Integer> {
}