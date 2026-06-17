package org.ashutosh.blogplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.UpdateProfileRequest;
import org.ashutosh.blogplatform.dto.UserProfileResponse;
import org.ashutosh.blogplatform.entity.User;
import org.ashutosh.blogplatform.repository.UserRepository;
import org.ashutosh.blogplatform.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserProfileResponse getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();
        return mapToResponse(user);
    }

    @Override
    public UserProfileResponse updateProfile(UpdateProfileRequest request) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        user.setName(request.getName());
        user.setBio(request.getBio());
        user.setWebsite(request.getWebsite());
        user.setLinkedin(request.getLinkedin());
        user.setTwitter(request.getTwitter());

        User updatedUser =
                userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    @Override
    public UserProfileResponse getUserById(Long userId) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return mapToResponse(user);
    }

    private UserProfileResponse mapToResponse(User user) {

        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .bio(user.getBio())
                .profilePicture(user.getProfilePicture())
                .website(user.getWebsite())
                .linkedin(user.getLinkedin())
                .twitter(user.getTwitter())
                .build();
    }
}
