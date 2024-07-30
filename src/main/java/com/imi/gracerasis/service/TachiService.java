package com.imi.gracerasis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class TachiService {

    private final WebClient webClient;
    //private final OAuth2AuthorizedClientManager authorizedClientManager;

    private static final String BASE_URL = "https://kamai.tachi.ac/api/v1";

    public TachiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();

    }
}
