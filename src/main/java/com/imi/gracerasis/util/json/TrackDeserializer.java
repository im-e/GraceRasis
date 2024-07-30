package com.imi.gracerasis.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.imi.gracerasis.model.DTO.Track;

import java.io.IOException;
import java.time.LocalDateTime;

public class TrackDeserializer extends StdDeserializer<Track> {

    public TrackDeserializer() {
        this(null);
    }

    public TrackDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Track deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        Track track = new Track();

        // Deserialize common fields
        track.setId(node.get("id").asInt());
        track.setTitle(node.get("title").asText());
        track.setArtist(node.get("artist").asText());
        track.setDbUpdatedAt(LocalDateTime.parse(node.get("db_updated_at").asText()));

        // Deserialize difficulties
        track.setNovice(deserializeDifficulty(node.get("novice"), Track.DifficultyType.NOVICE));
        track.setAdvanced(deserializeDifficulty(node.get("advanced"), Track.DifficultyType.ADVANCED));
        track.setExhaust(deserializeDifficulty(node.get("exhaust"), Track.DifficultyType.EXHAUST));

        // Handle final difficulty
        if (node.has("maximum")) {
            track.setFinalDifficulty(deserializeDifficulty(node.get("maximum"), Track.DifficultyType.MAXIMUM));
        } else if (node.has("heavenly")) {
            track.setFinalDifficulty(deserializeDifficulty(node.get("heavenly"), Track.DifficultyType.HEAVENLY));
        } else if (node.has("infinite")) {
            track.setFinalDifficulty(deserializeDifficulty(node.get("infinite"), Track.DifficultyType.INFINITE));
        } else if (node.has("exceed")) {
            track.setFinalDifficulty(deserializeDifficulty(node.get("exceed"), Track.DifficultyType.EXCEED));
        }

        return track;
    }

    private Track.Difficulty deserializeDifficulty(JsonNode node, Track.DifficultyType type) {
        Track.Difficulty difficulty = new Track.Difficulty();
        difficulty.setLevel(node.get("level").asInt());
        difficulty.setEffectedBy(node.get("effected_by").asText());
        difficulty.setIllustratedBy(node.get("illustrated_by").asText());
        difficulty.setType(type);
        return difficulty;
    }
}