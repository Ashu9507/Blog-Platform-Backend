package org.ashutosh.blogplatform.service;

import org.ashutosh.blogplatform.dto.UpdateProfileRequest;
import org.ashutosh.blogplatform.dto.UserProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserProfileResponse getCurrentUser();

    UserProfileResponse updateProfile(
            UpdateProfileRequest request);

    UserProfileResponse getUserById(Long userId);
}
