package com.imi.gracerasis.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "music")
public class Music {

    @Id
    private int id;
    private String label;
    private String title;
    private String titleYomigana;
    private String artist;
    private String artistYomigana;
    private String ascii;
    private long bpmMax;
    private long bpmMin;
    private long distributionDate;
    private int volume;
    private int bgNo;
    private long genre;
    private boolean isFixed;
    private int version;
    private int demoPri;
    private int infVer;
    private String jacketLink;

    private int noviceLevel;
    private int advancedLevel;
    private int exhaustLevel;
    private int finalLevel;
    private String finalDifficulty;

    public String getFinalDifficulty() {
        return finalDifficulty;
    }

    public void setFinalDifficulty(String finalDifficulty) {
        this.finalDifficulty = finalDifficulty;
    }

    public int getNoviceLevel() {
        return noviceLevel;
    }

    public void setNoviceLevel(int noviceLevel) {
        this.noviceLevel = noviceLevel;
    }

    public int getAdvancedLevel() {
        return advancedLevel;
    }

    public void setAdvancedLevel(int advancedLevel) {
        this.advancedLevel = advancedLevel;
    }

    public int getExhaustLevel() {
        return exhaustLevel;
    }

    public void setExhaustLevel(int exhaustLevel) {
        this.exhaustLevel = exhaustLevel;
    }

    public int getFinalLevel() {
        return finalLevel;
    }

    public void setFinalLevel(int finalLevel) {
        this.finalLevel = finalLevel;
    }

    public String getJacketLink() {
        return jacketLink;
    }

    public void setJacketLink(String jacketLink) {
        this.jacketLink = jacketLink;
    }

    public String getAscii() {
        return ascii;
    }

    public void setAscii(String ascii) {
        this.ascii = ascii;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleYomigana() {
        return titleYomigana;
    }

    public void setTitleYomigana(String titleYomigana) {
        this.titleYomigana = titleYomigana;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtistYomigana() {
        return artistYomigana;
    }

    public void setArtistYomigana(String artistYomigana) {
        this.artistYomigana = artistYomigana;
    }

    public long getBpmMax() {
        return bpmMax;
    }

    public void setBpmMax(long bpmMax) {
        this.bpmMax = bpmMax;
    }

    public long getBpmMin() {
        return bpmMin;
    }

    public void setBpmMin(long bpmMin) {
        this.bpmMin = bpmMin;
    }

    public long getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(long distributionDate) {
        this.distributionDate = distributionDate;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getBgNo() {
        return bgNo;
    }

    public void setBgNo(int bgNo) {
        this.bgNo = bgNo;
    }

    public long getGenre() {
        return genre;
    }

    public void setGenre(long genre) {
        this.genre = genre;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getDemoPri() {
        return demoPri;
    }

    public void setDemoPri(int demoPri) {
        this.demoPri = demoPri;
    }

    public int getInfVer() {
        return infVer;
    }

    public void setInfVer(int infVer) {
        this.infVer = infVer;
    }
}
