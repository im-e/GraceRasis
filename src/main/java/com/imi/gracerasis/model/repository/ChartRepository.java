package com.imi.gracerasis.model.repository;

import com.imi.gracerasis.model.entity.Chart;
import com.imi.gracerasis.model.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChartRepository extends MongoRepository<Chart, String> {

    List<Chart> findChartByLevel(int level);
    List<Chart> findChartByMusicId(int musicId);
}
