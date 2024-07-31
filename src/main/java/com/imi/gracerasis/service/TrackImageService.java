package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Track;
import com.imi.gracerasis.model.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackImageService {

    private final Path gameDataPath;
    private final MongoTemplate mongoTemplate;
    private final TrackRepository trackRepository;

    public TrackImageService(@Value("${game.data.path}") String gameDataPath,
                             MongoTemplate mongoTemplate,
                             TrackRepository trackRepository) {
        this.gameDataPath = Paths.get(gameDataPath);
        this.mongoTemplate = mongoTemplate;
        this.trackRepository = trackRepository;
    }

    public void processTrack(Track track) throws IOException {

        Path trackPath = gameDataPath.resolve("contents/data/music/" + track.getId() + "_*");

        List<Path> matchingDirs = Files.find(gameDataPath, 1,
                        (path, attr) -> path.getFileName().toString().startsWith(track.getId() + "_"))
                .toList();

        if (matchingDirs.isEmpty()) {
            throw new FileNotFoundException("No directory found for track " + track.getId());
        }

        Path trackDir = matchingDirs.getFirst();
        List<byte[]> jacketImages = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String jacketName = "jk_" + track.getId() + "_" + i;
            Path jacketPath = trackDir.resolve(jacketName + ".jpg");

            if (Files.exists(jacketPath)) {
                byte[] imageData = Files.readAllBytes(jacketPath);
                jacketImages.add(imageData);
            }
        }

        track.setJacketImages(jacketImages);
        // Set other track properties as needed

        mongoTemplate.save(track);
    }

}
