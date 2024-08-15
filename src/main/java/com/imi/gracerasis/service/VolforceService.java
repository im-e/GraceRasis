package com.imi.gracerasis.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class VolforceService {

    private static final Map<String, Double> CLEAR_MEDAL_BONUS = new HashMap<>();
    private static final Map<String, Double> GRADE_BONUS = new HashMap<>();

    static {
        CLEAR_MEDAL_BONUS.put("PUC", 1.10);
        CLEAR_MEDAL_BONUS.put("UC", 1.05);
        CLEAR_MEDAL_BONUS.put("EXC", 1.02);
        CLEAR_MEDAL_BONUS.put("C", 1.00);
        CLEAR_MEDAL_BONUS.put("PLAYED", 0.50);

        GRADE_BONUS.put("S", 1.05);
        GRADE_BONUS.put("AAA+", 1.02);
        GRADE_BONUS.put("AAA", 1.00);
        GRADE_BONUS.put("AA+", 0.97);
    }

    public double calculateVolforce(int level, int score, String clearMedal) {
        int playerScore = score;
        if (score <= 1000) playerScore = score * 10000;

        double normalizedScore = (double) playerScore / 1000000000;
        double gradeBonus = calculateGradeBonus(playerScore);
        double clearMedalBonus = CLEAR_MEDAL_BONUS.getOrDefault(clearMedal.toUpperCase(), 1.00);

        double volforce = ((level * 2) * normalizedScore * gradeBonus * clearMedalBonus);
        return Math.floor(volforce * 1000) / 1000; // Truncate to 3 decimal places
    }

    private double calculateGradeBonus(int score) {
        int normalizedScore = score / 1000; // Convert to thousands
        if (normalizedScore >= 990) return GRADE_BONUS.get("S");
        if (normalizedScore >= 980) return GRADE_BONUS.get("AAA+");
        if (normalizedScore >= 970) return GRADE_BONUS.get("AAA");
        if (normalizedScore >= 950) return GRADE_BONUS.get("AA+");
        return 1.00; // Default grade bonus
    }
}
