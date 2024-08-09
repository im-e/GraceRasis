package com.imi.gracerasis.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "charts")
public class Chart {

    @Id
    private String id;

    private int musicId; // Reference to the Music document
    private String difficulty; // "novice", "advanced", "exhaust", "infinite", or "maximum"

    private String jacketLink;

    private int level;
    private String illustratedBy;
    private String effectedBy;
    private int price;
    private int limited;
    private int jacketPrint;
    private int jacketMask;
    private int maxExScore;

    private int radarNotes;
    private int radarPeak;
    private int radarTsumami;
    private int radarTricky;
    private int radarHandTrip;
    private int radarOneHand;

    public String getJacketLink() {
        return jacketLink;
    }

    public void setJacketLink(String jacketLink) {
        this.jacketLink = jacketLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIllustratedBy() {
        return illustratedBy;
    }

    public void setIllustratedBy(String illustratedBy) {
        this.illustratedBy = illustratedBy;
    }

    public String getEffectedBy() {
        return effectedBy;
    }

    public void setEffectedBy(String effectedBy) {
        this.effectedBy = effectedBy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }

    public int getJacketPrint() {
        return jacketPrint;
    }

    public void setJacketPrint(int jacketPrint) {
        this.jacketPrint = jacketPrint;
    }

    public int getJacketMask() {
        return jacketMask;
    }

    public void setJacketMask(int jacketMask) {
        this.jacketMask = jacketMask;
    }

    public int getMaxExScore() {
        return maxExScore;
    }

    public void setMaxExScore(int maxExScore) {
        this.maxExScore = maxExScore;
    }

    public int getRadarNotes() {
        return radarNotes;
    }

    public void setRadarNotes(int radarNotes) {
        this.radarNotes = radarNotes;
    }

    public int getRadarPeak() {
        return radarPeak;
    }

    public void setRadarPeak(int radarPeak) {
        this.radarPeak = radarPeak;
    }

    public int getRadarTsumami() {
        return radarTsumami;
    }

    public void setRadarTsumami(int radarTsumami) {
        this.radarTsumami = radarTsumami;
    }

    public int getRadarTricky() {
        return radarTricky;
    }

    public void setRadarTricky(int radarTricky) {
        this.radarTricky = radarTricky;
    }

    public int getRadarHandTrip() {
        return radarHandTrip;
    }

    public void setRadarHandTrip(int radarHandTrip) {
        this.radarHandTrip = radarHandTrip;
    }

    public int getRadarOneHand() {
        return radarOneHand;
    }

    public void setRadarOneHand(int radarOneHand) {
        this.radarOneHand = radarOneHand;
    }
}
