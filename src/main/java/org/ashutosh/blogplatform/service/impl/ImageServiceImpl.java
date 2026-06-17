package org.ashutosh.blogplatform.service.impl;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) {
        try {

            Map<?, ?> result =
                    cloudinary.uploader().upload(
                            file.getBytes(),
                            Map.of()
                    );

            return result.get("secure_url").toString();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Image upload failed", e);
        }
    }
}
