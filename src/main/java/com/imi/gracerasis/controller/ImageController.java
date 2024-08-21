package com.imi.gracerasis.controller;

import com.imi.gracerasis.service.GoogleCloudService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final GoogleCloudService storageService;

    public ImageController(GoogleCloudService storageService) {
        this.storageService = storageService;
    }

}