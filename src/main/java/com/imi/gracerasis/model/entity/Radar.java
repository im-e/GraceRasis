package com.imi.gracerasis.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "radar")
public class Radar {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "notes")
    private Integer notes;

    @Column(name = "peak")
    private Integer peak;

    @Column(name = "tsumami")
    private Integer tsumami;

    @Column(name = "tricky")
    private Integer tricky;

    @Column(name = "hand_trip")
    private Integer handTrip;

    @Column(name = "one_hand")
    private Integer oneHand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotes() {
        return notes;
    }

    public void setNotes(Integer notes) {
        this.notes = notes;
    }

    public Integer getPeak() {
        return peak;
    }

    public void setPeak(Integer peak) {
        this.peak = peak;
    }

    public Integer getTsumami() {
        return tsumami;
    }

    public void setTsumami(Integer tsumami) {
        this.tsumami = tsumami;
    }

    public Integer getTricky() {
        return tricky;
    }

    public void setTricky(Integer tricky) {
        this.tricky = tricky;
    }

    public Integer getHandTrip() {
        return handTrip;
    }

    public void setHandTrip(Integer handTrip) {
        this.handTrip = handTrip;
    }

    public Integer getOneHand() {
        return oneHand;
    }

    public void setOneHand(Integer oneHand) {
        this.oneHand = oneHand;
    }

}