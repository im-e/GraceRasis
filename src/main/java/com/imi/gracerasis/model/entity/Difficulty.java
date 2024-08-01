package com.imi.gracerasis.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "difficulty")
public class Difficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "difnum")
    private Integer difnum;

    @Column(name = "illustrator")
    private String illustrator;

    @Column(name = "effected_by")
    private String effectedBy;

    @Column(name = "price")
    private Integer price;

    @Column(name = "limited")
    private Integer limited;

    @Column(name = "jacket_print")
    private Integer jacketPrint;

    @Column(name = "jacket_mask")
    private Integer jacketMask;

    @Column(name = "max_exscore")
    private Integer maxExscore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "radar_id")
    private Radar radar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDifnum() {
        return difnum;
    }

    public void setDifnum(Integer difnum) {
        this.difnum = difnum;
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public String getEffectedBy() {
        return effectedBy;
    }

    public void setEffectedBy(String effectedBy) {
        this.effectedBy = effectedBy;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLimited() {
        return limited;
    }

    public void setLimited(Integer limited) {
        this.limited = limited;
    }

    public Integer getJacketPrint() {
        return jacketPrint;
    }

    public void setJacketPrint(Integer jacketPrint) {
        this.jacketPrint = jacketPrint;
    }

    public Integer getJacketMask() {
        return jacketMask;
    }

    public void setJacketMask(Integer jacketMask) {
        this.jacketMask = jacketMask;
    }

    public Integer getMaxExscore() {
        return maxExscore;
    }

    public void setMaxExscore(Integer maxExscore) {
        this.maxExscore = maxExscore;
    }

    public Radar getRadar() {
        return radar;
    }

    public void setRadar(Radar radar) {
        this.radar = radar;
    }

}