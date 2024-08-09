package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.MusicXml;
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
public class ChartJacketService {

    private final Path gameDataPath;

    public ChartJacketService(@Value("${game.data.path}") String gameDataPath) {
        this.gameDataPath = Paths.get(gameDataPath);
    }

    private String getPaddedTrackId(Integer trackId) {
        return String.format("%04d", trackId);
    }

    public List<String> getJacketFilepaths(MusicXml musicXml) throws IOException {

        Path musicPath = gameDataPath.resolve("contents").resolve("data").resolve("music");
        String paddedId = getPaddedTrackId(musicXml.getId());

        List<Path> matchingDirs = Files.walk(musicPath, 1)
                .filter(Files::isDirectory)
                .filter(path -> path.getFileName().toString().startsWith(paddedId + "_"))
                .toList();

        if (matchingDirs.isEmpty()) {
            throw new FileNotFoundException("No directory found for track " + paddedId
                    + " at: " + musicPath.toAbsolutePath());
        }

        Path trackDir = matchingDirs.getFirst();
        List<String> jacketImagePaths = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String jacketName = "jk_" + paddedId + "_" + i;
            Path jacketPath = trackDir.resolve(jacketName + ".png");

            if (Files.exists(jacketPath)) {
                jacketImagePaths.add(jacketPath.toAbsolutePath().toString());
            }
        }

        return jacketImagePaths;
    }

}
