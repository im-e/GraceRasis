package com.imi.gracerasis.controller;

import com.imi.gracerasis.service.GoogleCloudStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final GoogleCloudStorageService storageService;

    public ImageController(GoogleCloudStorageService storageService) {
        this.storageService = storageService;
    }

}