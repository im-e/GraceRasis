package com.imi.gracerasis.model.DTO;

import jakarta.xml.bind.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@XmlRootElement(name = "music")
@XmlAccessorType(XmlAccessType.FIELD)
public class Music {

    @XmlAttribute(name = "id")
    private int id;

    @XmlElement(name = "info")
    private MusicInfo info;

    @XmlElement(name = "difficulty")
    private MusicDifficulty difficulty;

    private List<byte[]> jacketImages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<byte[]> getJacketImages() {
        return jacketImages;
    }

    public void setJacketImages(List<byte[]> jacketImages) {
        this.jacketImages = jacketImages;
    }


    public static class MusicInfo {
        @XmlElement(name = "label")
        private String label;

        @XmlElement(name = "title_name")
        private String title;

        @XmlElement(name = "title_yomigana")
        private String titleYomigana;

        @XmlElement(name = "artist_name")
        private String artist;

        @XmlElement(name = "artist_yomigana")
        private String artistYomigana;

        @XmlElement(name = "ascii")
        private String ascii;

        @XmlElement(name = "bpm_max")
        private long bpmMax;

        @XmlElement(name = "bpm_min")
        private long bpmMin;

        @XmlElement(name = "distribution_date")
        private long distributionDate;

        @XmlElement(name = "volume")
        private int volume;

        @XmlElement(name = "bg_no")
        private int bgNo;

        @XmlElement(name = "genre")
        private long genre;

        @XmlElement(name = "is_fixed")
        private boolean isFixed;

        @XmlElement(name = "version")
        private int version;

        @XmlElement(name = "demo_pri")
        private int demoPri;

        @XmlElement(name = "inf_ver")
        private int infVer;

        public void setLabel(String label) {
            this.label = label;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitleYomigana(String titleYomigana) {
            this.titleYomigana = titleYomigana;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public void setArtistYomigana(String artistYomigana) {
            this.artistYomigana = artistYomigana;
        }

        public void setAscii(String ascii) {
            this.ascii = ascii;
        }

        public void setBpmMax(long bpmMax) {
            this.bpmMax = bpmMax;
        }

        public void setBpmMin(long bpmMin) {
            this.bpmMin = bpmMin;
        }

        public void setDistributionDate(long distributionDate) {
            this.distributionDate = distributionDate;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public void setBgNo(int bgNo) {
            this.bgNo = bgNo;
        }

        public void setGenre(long genre) {
            this.genre = genre;
        }

        public void setFixed(boolean fixed) {
            isFixed = fixed;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public void setDemoPri(int demoPri) {
            this.demoPri = demoPri;
        }

        public void setInfVer(int infVer) {
            this.infVer = infVer;
        }
    }

    public static class MusicDifficulty {
        @XmlElement(name = "novice")
        private Difficulty novice;

        @XmlElement(name = "advanced")
        private Difficulty advanced;

        @XmlElement(name = "exhaust")
        private Difficulty exhaust;

        @XmlElement(name = "infinite")
        private Difficulty infinite;

        public void setNovice(Difficulty novice) {
            this.novice = novice;
        }

        public void setAdvanced(Difficulty advanced) {
            this.advanced = advanced;
        }

        public void setExhaust(Difficulty exhaust) {
            this.exhaust = exhaust;
        }


        public void setInfinite(Difficulty infinite) {
            this.infinite = infinite;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Difficulty {
        @XmlElement(name = "difnum")
        private int level;

        @XmlElement(name = "illustrator")
        private String illustratedBy;

        @XmlElement(name = "effected_by")
        private String effectedBy;

        @XmlElement(name = "price")
        private int price;

        @XmlElement(name = "limited")
        private int limited;

        @XmlElement(name = "jacket_print")
        private int jacketPrint;

        @XmlElement(name = "jacket_mask")
        private int jacketMask;

        @XmlElement(name = "max_exscore")
        private int maxExscore;

        @XmlElement(name = "radar")
        private Radar radar;

        public void setLevel(int level) {
            this.level = level;
        }

        public void setIllustratedBy(String illustratedBy) {
            this.illustratedBy = illustratedBy;
        }

        public void setEffectedBy(String effectedBy) {
            this.effectedBy = effectedBy;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setLimited(int limited) {
            this.limited = limited;
        }

        public void setJacketPrint(int jacketPrint) {
            this.jacketPrint = jacketPrint;
        }

        public void setJacketMask(int jacketMask) {
            this.jacketMask = jacketMask;
        }

        public void setMaxExscore(int maxExscore) {
            this.maxExscore = maxExscore;
        }

        public void setRadar(Radar radar) {
            this.radar = radar;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Radar {
        @XmlElement(name = "notes")
        private int notes;

        @XmlElement(name = "peak")
        private int peak;

        @XmlElement(name = "tsumami")
        private int tsumami;

        @XmlElement(name = "tricky")
        private int tricky;

        @XmlElement(name = "hand-trip")
        private int handTrip;

        @XmlElement(name = "one-hand")
        private int oneHand;

        public int getNotes() {
            return notes;
        }

        public void setNotes(int notes) {
            this.notes = notes;
        }

        public int getPeak() {
            return peak;
        }

        public void setPeak(int peak) {
            this.peak = peak;
        }

        public int getTsumami() {
            return tsumami;
        }

        public void setTsumami(int tsumami) {
            this.tsumami = tsumami;
        }

        public int getTricky() {
            return tricky;
        }

        public void setTricky(int tricky) {
            this.tricky = tricky;
        }

        public int getHandTrip() {
            return handTrip;
        }

        public void setHandTrip(int handTrip) {
            this.handTrip = handTrip;
        }

        public int getOneHand() {
            return oneHand;
        }

        public void setOneHand(int oneHand) {
            this.oneHand = oneHand;
        }

    }

}