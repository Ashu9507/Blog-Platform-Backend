package org.ashutosh.blogplatform.controller;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.UpdateProfileRequest;
import org.ashutosh.blogplatform.dto.UserProfileResponse;
import org.ashutosh.blogplatform.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getCurrentUser() {

        return ResponseEntity.ok(
                userService.getCurrentUser());
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.ok(
                userService.updateProfile(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id));
    }
}
