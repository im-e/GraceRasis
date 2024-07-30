package com.imi.gracerasis.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class NearNoahService {

    private final WebClient webClient;

    private static final String BASE_URL = "https://nearnoah.net/api";

    public NearNoahService(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public String getTrackData(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("getTrackData.json")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getTrackData(int level)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("getTrackData.json")
                        .queryParam("level", level)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getTrackData(String artist){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("getTrackData.json")
                        .queryParam("artist", artist)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
