package com.imi.gracerasis.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GoogleCloudStorageService {

    private final Logger logger = Logger.getLogger(GoogleCloudStorageService.class.getName());

    @Autowired
    private Storage storage;

    @Value("${gcs.bucket.name}")
    private String bucketName;

    public List<String> uploadJackets(List<String> filePaths) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (String filePath : filePaths) {
            Path path = Path.of(filePath);
            String fileName = path.getFileName().toString();

            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(Files.probeContentType(path))
                    .build();

            Blob blob = storage.create(blobInfo, Files.readAllBytes(path));
            uploadedUrls.add(blob.getMediaLink());
        }

        logger.info("Uploaded " + uploadedUrls.size() + " Jackets");

        return uploadedUrls;
    }
}