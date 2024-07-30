package com.imi.gracerasis;

import com.imi.gracerasis.service.TrackPopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.imi.gracerasis.model.repository")
public class GraceRasisApplication implements CommandLineRunner {

    private final TrackPopulationService trackPopulationService;

    public GraceRasisApplication(TrackPopulationService trackPopulationService) {
        this.trackPopulationService = trackPopulationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GraceRasisApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //trackPopulationService.populateDatabase();
    }

}
