package com.imi.gracerasis.model.entity;

import java.util.List;

public class Song {

    private Music music;
    private List<Chart> charts;

    public List<Chart> getCharts() {
        return charts;
    }

    public void setCharts(List<Chart> charts) {
        this.charts = charts;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
