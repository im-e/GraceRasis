package com.imi.gracerasis.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.imi.gracerasis.util.json.TrackDeserializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "tracks")
@JsonDeserialize(using = TrackDeserializer.class)
public class Track {

    @Id
    private int id;
    private String title;
    private String artist;
    @JsonProperty("db_updated_at")
    private Date dbUpdatedAt;
    private Difficulty novice;
    private Difficulty advanced;
    private Difficulty exhaust;
    @JsonProperty("maximum")
    private Difficulty finalDifficulty; // This can be Maximum, Heavenly, Infinite, or Exceed

    private List<byte[]> jacketImages;

    @Override
    public String toString() {
        return "Track {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", dbUpdatedAt=" + dbUpdatedAt +
                ", novice=" + novice +
                ", advanced=" + advanced +
                ", exhaust=" + exhaust +
                ", finalDifficulty=" + finalDifficulty +
                '}';
    }

    public Difficulty getFinalDifficulty() { return finalDifficulty; }
    public void setFinalDifficulty(Difficulty finalDifficulty) { this.finalDifficulty = finalDifficulty; }

    public int getId() {  return id; }

    public void setId(int id) { this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getArtist() { return artist;}

    public void setArtist(String artist) {this.artist = artist;}

    public Date getDbUpdatedAt() {return dbUpdatedAt;}

    public void setDbUpdatedAt(Date dbUpdatedAt) {this.dbUpdatedAt = dbUpdatedAt;}

    public Difficulty getNovice() {return novice;}

    public void setNovice(Difficulty novice) { this.novice = novice;}

    public Difficulty getAdvanced() {return advanced;}

    public void setAdvanced(Difficulty advanced) {this.advanced = advanced;}

    public Difficulty getExhaust() { return exhaust;}

    public void setExhaust(Difficulty exhaust) { this.exhaust = exhaust;}

    public List<byte[]> getJacketImages() { return jacketImages;}

    public void setJacketImages(List<byte[]> jacketImages) {this.jacketImages = jacketImages;}

    public static class Difficulty {
        private int level;
        @JsonProperty("effected_by")
        private String effectedBy;
        @JsonProperty("illustrated_by")
        private String illustratedBy;
        private DifficultyType type;

        public int getLevel() {return level;}

        public void setLevel(int level) {this.level = level;}

        public String getEffectedBy() {return effectedBy;}

        public void setEffectedBy(String effectedBy) {this.effectedBy = effectedBy;}

        public String getIllustratedBy() {return illustratedBy;}

        public void setIllustratedBy(String illustratedBy) {this.illustratedBy = illustratedBy;}

        public DifficultyType getType() { return type; }
        public void setType(DifficultyType type) { this.type = type; }
    }

    public enum DifficultyType {
        NOVICE, ADVANCED, EXHAUST, MAXIMUM, HEAVENLY, INFINITE, EXCEED
    }
}