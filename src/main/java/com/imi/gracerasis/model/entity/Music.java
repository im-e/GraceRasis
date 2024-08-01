package com.imi.gracerasis.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    private MusicInfo info;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "difficulty_id", nullable = false)
    private MusicDifficulty difficulty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MusicInfo getInfo() {
        return info;
    }

    public void setInfo(MusicInfo info) {
        this.info = info;
    }

    public MusicDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(MusicDifficulty difficulty) {
        this.difficulty = difficulty;
    }

}