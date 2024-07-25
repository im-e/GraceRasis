package com.imi.gracerasis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.reactive.function.client.WebClient;

public class TachiService {

    private final WebClient webClient;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Value("${tachiapi.key}")
    private String apiKey;

    public TachiService(WebClient webClient, OAuth2AuthorizedClientManager authorizedClientManager) {
        this.webClient = webClient;
        this.authorizedClientManager = authorizedClientManager;
    }




    private String getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("GraceAndRasis")
                .principal("TachiService")
                .build();

        OAuth2AuthorizedClient authorizedClient =
                authorizedClientManager.authorize(authorizeRequest);

        assert authorizedClient != null;
        return authorizedClient.getAccessToken().getTokenValue();
    }

}
