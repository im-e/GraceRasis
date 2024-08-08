package com.imi.gracerasis;

import com.imi.gracerasis.service.DatabasePopulationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.imi.gracerasis.model.repository")
public class GraceRasisApplication implements CommandLineRunner {

    private final DatabasePopulationService trackPopulationService;

    public GraceRasisApplication(DatabasePopulationService trackPopulationService) {
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
