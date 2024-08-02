package com.imi.gracerasis.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "music")
public class MusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private MusicInfoEntity info;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "charts_id", referencedColumnName = "id")
    private ChartsEntity charts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MusicInfoEntity getInfo() {
        return info;
    }

    public void setInfo(MusicInfoEntity info) {
        this.info = info;
    }

    public ChartsEntity getCharts() {
        return charts;
    }

    public void setCharts(ChartsEntity charts) {
        this.charts = charts;
    }

    // Getters and setters

    @Entity
    @Table(name = "musicinfo")
    public static class MusicInfoEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "label")
        private String label;

        @Column(name = "title_name")
        private String title;

        @Column(name = "title_yomigana")
        private String titleYomigana;

        @Column(name = "artist_name")
        private String artist;

        @Column(name = "artist_yomigana")
        private String artistYomigana;

        @Column(name = "ascii")
        private String ascii;

        @Column(name = "bpm_max")
        private long bpmMax;

        @Column(name = "bpm_min")
        private long bpmMin;

        @Column(name = "distribution_date")
        private long distributionDate;

        @Column(name = "volume")
        private int volume;

        @Column(name = "bg_no")
        private int bgNo;

        @Column(name = "genre")
        private long genre;

        @Column(name = "is_fixed")
        private boolean isFixed;

        @Column(name = "version")
        private int version;

        @Column(name = "demo_pri")
        private int demoPri;

        @Column(name = "inf_ver")
        private int infVer;

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

        public String getAscii() {
            return ascii;
        }

        public void setAscii(String ascii) {
            this.ascii = ascii;
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

        // Getters and setters
    }

    @Entity
    @Table(name = "charts")
    public static class ChartsEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
        @JoinColumn(name = "novice_id", referencedColumnName = "id")
        private DifficultyEntity novice;

        @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
        @JoinColumn(name = "advanced_id", referencedColumnName = "id")
        private DifficultyEntity advanced;

        @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
        @JoinColumn(name = "exhaust_id", referencedColumnName = "id")
        private DifficultyEntity exhaust;

        @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
        @JoinColumn(name = "infinite_id", referencedColumnName = "id")
        private DifficultyEntity infinite;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public DifficultyEntity getNovice() {
            return novice;
        }

        public void setNovice(DifficultyEntity novice) {
            this.novice = novice;
        }

        public DifficultyEntity getAdvanced() {
            return advanced;
        }

        public void setAdvanced(DifficultyEntity advanced) {
            this.advanced = advanced;
        }

        public DifficultyEntity getExhaust() {
            return exhaust;
        }

        public void setExhaust(DifficultyEntity exhaust) {
            this.exhaust = exhaust;
        }

        public DifficultyEntity getInfinite() {
            return infinite;
        }

        public void setInfinite(DifficultyEntity infinite) {
            this.infinite = infinite;
        }

        // Getters and setters
    }

    @Entity
    @Table(name = "difficulty")
    public static class DifficultyEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "level")
        private int level;

        @Column(name = "illustrator")
        private String illustratedBy;

        @Column(name = "effected_by")
        private String effectedBy;

        @Column(name = "price")
        private int price;

        @Column(name = "limited")
        private int limited;

        @Column(name = "jacket_print")
        private int jacketPrint;

        @Column(name = "jacket_mask")
        private int jacketMask;

        @Column(name = "max_exscore")
        private int maxExscore;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "radar_id", referencedColumnName = "id")
        private RadarEntity radar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getMaxExscore() {
            return maxExscore;
        }

        public void setMaxExscore(int maxExscore) {
            this.maxExscore = maxExscore;
        }

        public RadarEntity getRadar() {
            return radar;
        }

        public void setRadar(RadarEntity radar) {
            this.radar = radar;
        }

        // Getters and setters
    }

    @Entity
    @Table(name = "radar")
    public static class RadarEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "notes")
        private int notes;

        @Column(name = "peak")
        private int peak;

        @Column(name = "tsumami")
        private int tsumami;

        @Column(name = "tricky")
        private int tricky;

        @Column(name = "hand_trip")
        private int handTrip;

        @Column(name = "one_hand")
        private int oneHand;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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