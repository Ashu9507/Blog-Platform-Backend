package org.ashutosh.blogplatform.controller;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.LikeResponse;
import org.ashutosh.blogplatform.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{blogId}")
    public ResponseEntity<LikeResponse> likeBlog(@PathVariable Long blogId) {

        return ResponseEntity.ok(
                likeService.likeBlog(blogId));
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<LikeResponse> unlikeBlog(@PathVariable Long blogId) {

        return ResponseEntity.ok(
                likeService.unlikeBlog(blogId));
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<LikeResponse> getLikes(@PathVariable Long blogId) {

        return ResponseEntity.ok(
                likeService.getLikes(blogId));
    }
}
