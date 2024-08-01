package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Music;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackImageService {

    private final Path gameDataPath;

    public TrackImageService(@Value("${game.data.path}") String gameDataPath) {
        this.gameDataPath = Paths.get(gameDataPath);
    }

    private String getPaddedTrackId(Integer trackId) {
        return String.format("%04d", trackId);
    }

    public void processTrack(Music music) throws IOException {

        Path musicPath = gameDataPath.resolve("contents").resolve("data").resolve("music");
        String paddedId = getPaddedTrackId(music.getId());


        List<Path> matchingDirs = Files.walk(musicPath, 1)
                .filter(Files::isDirectory)
                .filter(path -> path.getFileName().toString().startsWith(paddedId + "_"))
                .toList();

        if (matchingDirs.isEmpty()) {
            throw new FileNotFoundException("No directory found for track " + paddedId
            + " at: " + musicPath.toAbsolutePath());
        }

        Path trackDir = matchingDirs.getFirst();
        List<byte[]> jacketImages = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String jacketName = "jk_" + paddedId + "_" + i;
            Path jacketPath = trackDir.resolve(jacketName + ".png");

            if (Files.exists(jacketPath)) {
                byte[] imageData = Files.readAllBytes(jacketPath);
                jacketImages.add(imageData);
            }
            else
            {
                System.out.println("No jacket found for track " + paddedId + " at: " + jacketPath);
            }
        }

        //music.setJacketImages(jacketImages);
    }

}
