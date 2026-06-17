package org.ashutosh.blogplatform.controller;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.CommentResponse;
import org.ashutosh.blogplatform.dto.CreateCommentRequest;
import org.ashutosh.blogplatform.service.CommentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentsService commentsService;

    @PostMapping("/{blogId}")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Long blogId,
            @RequestBody CreateCommentRequest request) {

        return ResponseEntity.ok(commentsService.addComment(blogId, request));
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<List<CommentResponse>>
    getComments(@PathVariable Long blogId) {

        return ResponseEntity.ok(
                commentsService.getCommentsByBlog(blogId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId) {

        commentsService.deleteComment(commentId);

        return ResponseEntity.ok(
                "Comment deleted successfully");
    }
}
