package com.imi.gracerasis;

import com.imi.gracerasis.service.DatabaseManagementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.imi.gracerasis.model.repository")
public class GraceRasisApplication implements CommandLineRunner {

    private final DatabaseManagementService databaseManagementService;

    public GraceRasisApplication(DatabaseManagementService trackPopulationService) {
        this.databaseManagementService = trackPopulationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GraceRasisApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //databasePopulationService.populateDatabase();
        //databaseManagementService.updateDatabase();
    }

}
