package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicEntityRepository extends JpaRepository<MusicEntity, Integer> {
}