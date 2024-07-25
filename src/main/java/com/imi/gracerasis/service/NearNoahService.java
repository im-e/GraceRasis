package com.imi.gracerasis.service;

import org.springframework.web.reactive.function.client.WebClient;

public class NearNoahService {
    private final WebClient webClient;

    public NearNoahService(WebClient webClient) {
        this.webClient = webClient;
    }

    private static final String BASE_URL = "https://nearnoah.net/api";

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
