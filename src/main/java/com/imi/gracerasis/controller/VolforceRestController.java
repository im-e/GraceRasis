package com.imi.gracerasis.controller;

import com.imi.gracerasis.service.VolforceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class VolforceRestController {

    private final VolforceService volforceService;

    public VolforceRestController(VolforceService volforceService) {
        this.volforceService = volforceService;
    }

    private static final List<Integer> LEVELS = Arrays.asList(16, 17, 18, 19, 20);
    private static final List<String> CLEAR_MEDALS = Arrays.asList("PUC", "UC", "EXC", "C", "PLAYED");


    private String formatVolforce(double volforce) {
        return String.format("%.3f", volforce);
    }

    @GetMapping("/volforce")
    public Map<String, Object> getCustomVolforce(
            @RequestParam int level,
            @RequestParam int score,
            @RequestParam(defaultValue = "C") String clearMedal) {
        Map<String, Object> result = new HashMap<>();
        result.put("level", level);
        result.put("score", score);
        result.put("clearMedal", clearMedal);
        result.put("volforce", formatVolforce(volforceService.calculateVolforce(level, score, clearMedal)));
        return result;
    }
}
