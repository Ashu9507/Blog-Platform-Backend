package org.ashutosh.blogplatform.controller;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(
            @RequestParam("file")
            MultipartFile file) {

        String imageUrl =
                imageService.uploadImage(file);

        return ResponseEntity.ok(
                Map.of(
                        "imageUrl",
                        imageUrl
                )
        );
    }
}
