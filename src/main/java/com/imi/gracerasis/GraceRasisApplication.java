package com.imi.gracerasis;

import com.imi.gracerasis.service.MusicPopulationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.imi.gracerasis.model.repository")
public class GraceRasisApplication implements CommandLineRunner {

    private final MusicPopulationService trackPopulationService;

    public GraceRasisApplication(MusicPopulationService trackPopulationService) {
        this.trackPopulationService = trackPopulationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GraceRasisApplication.class, args);
    }

    @Override
    public void run(String... args) {
        trackPopulationService.populateDatabase();
    }

}
