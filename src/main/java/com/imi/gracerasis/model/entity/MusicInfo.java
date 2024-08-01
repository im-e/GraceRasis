package com.imi.gracerasis.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "musicinfo")
public class MusicInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "label")
    private String label;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "title_yomigana")
    private String titleYomigana;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "artist_yomigana")
    private String artistYomigana;

    @Column(name = "ascii")
    private String ascii;

    @Column(name = "bpm_max")
    private Long bpmMax;

    @Column(name = "bpm_min")
    private Long bpmMin;

    @Column(name = "distribution_date")
    private Long distributionDate;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "bg_no")
    private Integer bgNo;

    @Column(name = "genre")
    private Long genre;

    @Column(name = "is_fixed")
    private Boolean isFixed;

    @Column(name = "version")
    private Integer version;

    @Column(name = "demo_pri")
    private Integer demoPri;

    @Column(name = "inf_ver")
    private Integer infVer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleYomigana() {
        return titleYomigana;
    }

    public void setTitleYomigana(String titleYomigana) {
        this.titleYomigana = titleYomigana;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistYomigana() {
        return artistYomigana;
    }

    public void setArtistYomigana(String artistYomigana) {
        this.artistYomigana = artistYomigana;
    }

    public String getAscii() {
        return ascii;
    }

    public void setAscii(String ascii) {
        this.ascii = ascii;
    }

    public Long getBpmMax() {
        return bpmMax;
    }

    public void setBpmMax(Long bpmMax) {
        this.bpmMax = bpmMax;
    }

    public Long getBpmMin() {
        return bpmMin;
    }

    public void setBpmMin(Long bpmMin) {
        this.bpmMin = bpmMin;
    }

    public Long getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(Long distributionDate) {
        this.distributionDate = distributionDate;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getBgNo() {
        return bgNo;
    }

    public void setBgNo(Integer bgNo) {
        this.bgNo = bgNo;
    }

    public Long getGenre() {
        return genre;
    }

    public void setGenre(Long genre) {
        this.genre = genre;
    }

    public Boolean getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDemoPri() {
        return demoPri;
    }

    public void setDemoPri(Integer demoPri) {
        this.demoPri = demoPri;
    }

    public Integer getInfVer() {
        return infVer;
    }

    public void setInfVer(Integer infVer) {
        this.infVer = infVer;
    }

}