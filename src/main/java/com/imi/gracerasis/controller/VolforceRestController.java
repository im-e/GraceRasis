package com.imi.gracerasis.controller;

import com.imi.gracerasis.service.VolforceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VolforceRestController {

    private final VolforceService volforceService;

    public VolforceRestController(VolforceService volforceService) {
        this.volforceService = volforceService;
    }

    @GetMapping("vf/calculate")
    public Map<String, Object> calculateVolforce(
            @RequestParam int level,
            @RequestParam int score,
            @RequestParam String clearMedal) {

        double volforce = volforceService.calculateVolforce(level, score, clearMedal);

        Map<String, Object> response = new HashMap<>();

        int playerScore = score;
        if (score <= 1000) playerScore = score * 10000;
        response.put("level", level);
        response.put("score", playerScore);
        response.put("clearMedal", clearMedal);
        response.put("volforce", volforce);

        return response;
    }
}