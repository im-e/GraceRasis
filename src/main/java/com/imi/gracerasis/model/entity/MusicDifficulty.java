package com.imi.gracerasis.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "musicdifficulty")
public class MusicDifficulty {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "novice_id")
    private Difficulty novice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advanced_id")
    private Difficulty advanced;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhaust_id")
    private Difficulty exhaust;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infinite_id")
    private Difficulty infinite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Difficulty getNovice() {
        return novice;
    }

    public void setNovice(Difficulty novice) {
        this.novice = novice;
    }

    public Difficulty getAdvanced() {
        return advanced;
    }

    public void setAdvanced(Difficulty advanced) {
        this.advanced = advanced;
    }

    public Difficulty getExhaust() {
        return exhaust;
    }

    public void setExhaust(Difficulty exhaust) {
        this.exhaust = exhaust;
    }

    public Difficulty getInfinite() {
        return infinite;
    }

    public void setInfinite(Difficulty infinite) {
        this.infinite = infinite;
    }

}