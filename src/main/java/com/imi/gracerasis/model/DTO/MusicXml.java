package com.imi.gracerasis.model.DTO;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "music")
@XmlAccessorType(XmlAccessType.NONE)
public class MusicXml {
    private int id;
    private MusicInfo info;
    private Charts charts;

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", info=" + info +
                ", charts=" + charts +
                '}';
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "info")
    public MusicInfo getInfo() {
        return info;
    }

    public void setInfo(MusicInfo info) {
        this.info = info;
    }

    @XmlElement(name = "difficulty")
    public Charts getCharts() {
        return charts;
    }

    public void setCharts(Charts charts) {
        this.charts = charts;
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class MusicInfo {
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

        @Override
        public String toString() {
            return "MusicInfo{" +
                    "label='" + label + '\'' +
                    ", title='" + title + '\'' +
                    ", titleYomigana='" + titleYomigana + '\'' +
                    ", artist='" + artist + '\'' +
                    ", artistYomigana='" + artistYomigana + '\'' +
                    ", ascii='" + ascii + '\'' +
                    ", bpmMax=" + bpmMax +
                    ", bpmMin=" + bpmMin +
                    ", distributionDate=" + distributionDate +
                    ", volume=" + volume +
                    ", bgNo=" + bgNo +
                    ", genre=" + genre +
                    ", isFixed=" + isFixed +
                    ", version=" + version +
                    ", demoPri=" + demoPri +
                    ", infVer=" + infVer +
                    '}';
        }

        @XmlElement(name = "label")
        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @XmlElement(name = "title_name")
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @XmlElement(name = "title_yomigana")
        public String getTitleYomigana() {
            return titleYomigana;
        }

        public void setTitleYomigana(String titleYomigana) {
            this.titleYomigana = titleYomigana;
        }

        @XmlElement(name = "artist_name")
        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        @XmlElement(name = "artist_yomigana")
        public String getArtistYomigana() {
            return artistYomigana;
        }

        public void setArtistYomigana(String artistYomigana) {
            this.artistYomigana = artistYomigana;
        }

        @XmlElement(name = "ascii")
        public String getAscii() {
            return ascii;
        }

        public void setAscii(String ascii) {
            this.ascii = ascii;
        }

        @XmlElement(name = "bpm_max")
        public long getBpmMax() {
            return bpmMax;
        }

        public void setBpmMax(long bpmMax) {
            this.bpmMax = bpmMax;
        }

        @XmlElement(name = "bpm_min")
        public long getBpmMin() {
            return bpmMin;
        }

        public void setBpmMin(long bpmMin) {
            this.bpmMin = bpmMin;
        }

        @XmlElement(name = "distribution_date")
        public long getDistributionDate() {
            return distributionDate;
        }

        public void setDistributionDate(long distributionDate) {
            this.distributionDate = distributionDate;
        }

        @XmlElement(name = "volume")
        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        @XmlElement(name = "bg_no")
        public int getBgNo() {
            return bgNo;
        }

        public void setBgNo(int bgNo) {
            this.bgNo = bgNo;
        }

        @XmlElement(name = "genre")
        public long getGenre() {
            return genre;
        }

        public void setGenre(long genre) {
            this.genre = genre;
        }

        @XmlElement(name = "is_fixed")
        public boolean isFixed() {
            return isFixed;
        }

        public void setFixed(boolean fixed) {
            isFixed = fixed;
        }

        @XmlElement(name = "version")
        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        @XmlElement(name = "demo_pri")
        public int getDemoPri() {
            return demoPri;
        }

        public void setDemoPri(int demoPri) {
            this.demoPri = demoPri;
        }

        @XmlElement(name = "inf_ver")
        public int getInfVer() {
            return infVer;
        }

        public void setInfVer(int infVer) {
            this.infVer = infVer;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Charts {
        private Difficulty novice;
        private Difficulty advanced;
        private Difficulty exhaust;
        private Difficulty infinite;
        private Difficulty maximum = null;


        @XmlElement(name = "novice")
        public Difficulty getNovice() {
            return novice;
        }

        public void setNovice(Difficulty novice) {
            this.novice = novice;
        }

        @XmlElement(name = "advanced")
        public Difficulty getAdvanced() {
            return advanced;
        }

        public void setAdvanced(Difficulty advanced) {
            this.advanced = advanced;
        }

        @XmlElement(name = "exhaust")
        public Difficulty getExhaust() {
            return exhaust;
        }

        public void setExhaust(Difficulty exhaust) {
            this.exhaust = exhaust;
        }

        @XmlElement(name = "infinite")
        public Difficulty getInfinite() {
            return infinite;
        }

        public void setInfinite(Difficulty infinite) {
            this.infinite = infinite;
        }

        @XmlElement(name = "maximum")
        public Difficulty getMaximum() { return maximum;}

        public void setMaximum(Difficulty maximum) { this.maximum = maximum; }

    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Difficulty {
        private int level;
        private String illustratedBy;
        private String effectedBy;
        private int price;
        private int limited;
        private int jacketPrint;
        private int jacketMask;
        private int maxExScore;
        private Radar radar;

        @XmlElement(name = "difnum")
        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @XmlElement(name = "illustrator")
        public String getIllustratedBy() {
            return illustratedBy;
        }

        public void setIllustratedBy(String illustratedBy) {
            this.illustratedBy = illustratedBy;
        }

        @XmlElement(name = "effected_by")
        public String getEffectedBy() {
            return effectedBy;
        }

        public void setEffectedBy(String effectedBy) {
            this.effectedBy = effectedBy;
        }

        @XmlElement(name = "price")
        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        @XmlElement(name = "limited")
        public int getLimited() {
            return limited;
        }

        public void setLimited(int limited) {
            this.limited = limited;
        }

        @XmlElement(name = "jacket_print")
        public int getJacketPrint() {
            return jacketPrint;
        }

        public void setJacketPrint(int jacketPrint) {
            this.jacketPrint = jacketPrint;
        }

        @XmlElement(name = "jacket_mask")
        public int getJacketMask() {
            return jacketMask;
        }

        public void setJacketMask(int jacketMask) {
            this.jacketMask = jacketMask;
        }

        @XmlElement(name = "max_exscore")
        public int getMaxExScore() {
            return maxExScore;
        }

        public void setMaxExScore(int maxExScore) {
            this.maxExScore = maxExScore;
        }

        @XmlElement(name = "radar")
        public Radar getRadar() {
            return radar;
        }

        public void setRadar(Radar radar) {
            this.radar = radar;
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Radar {
        private int notes;
        private int peak;
        private int tsumami;
        private int tricky;
        private int handTrip;
        private int oneHand;

        @XmlElement(name = "notes")
        public int getNotes() {
            return notes;
        }

        public void setNotes(int notes) {
            this.notes = notes;
        }

        @XmlElement(name = "peak")
        public int getPeak() {
            return peak;
        }

        public void setPeak(int peak) {
            this.peak = peak;
        }

        @XmlElement(name = "tsumami")
        public int getTsumami() {
            return tsumami;
        }

        public void setTsumami(int tsumami) {
            this.tsumami = tsumami;
        }

        @XmlElement(name = "tricky")
        public int getTricky() {
            return tricky;
        }

        public void setTricky(int tricky) {
            this.tricky = tricky;
        }

        @XmlElement(name = "hand-trip")
        public int getHandTrip() {
            return handTrip;
        }

        public void setHandTrip(int handTrip) {
            this.handTrip = handTrip;
        }

        @XmlElement(name = "one-hand")
        public int getOneHand() {
            return oneHand;
        }

        public void setOneHand(int oneHand) {
            this.oneHand = oneHand;
        }
    }
}