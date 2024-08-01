package com.imi.gracerasis.service;

import com.imi.gracerasis.model.DTO.Music;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class NearNoahService {

    private final WebClient webClient;

    private static final String BASE_URL = "https://nearnoah.net/api";

    public NearNoahService(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public List<Music> getTrackData() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("getTrackData.json")
                        .build())
                .retrieve()
                .bodyToFlux(Music.class)  // Use Flux for a stream of Track objects
                .collectList()            // Collect the Flux into a List
                .block();                 // Block to get the result (consider using reactive approach instead)
    }

    public List<Music> getTrackData(int level)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("getTrackData.json")
                        .queryParam("level", level)
                        .build())
                .retrieve()
                .bodyToFlux(Music.class)  // Use Flux for a stream of Track objects
                .collectList()            // Collect the Flux into a List
                .block();
    }

    public List<Music> getTrackData(String artist){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("getTrackData.json")
                        .queryParam("artist", artist)
                        .build())
                .retrieve()
                .bodyToFlux(Music.class)  // Use Flux for a stream of Track objects
                .collectList()            // Collect the Flux into a List
                .block();
    }
}
