package org.ashutosh.blogplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.CommentResponse;
import org.ashutosh.blogplatform.dto.CreateCommentRequest;
import org.ashutosh.blogplatform.entity.Blog;
import org.ashutosh.blogplatform.entity.Comment;
import org.ashutosh.blogplatform.entity.User;
import org.ashutosh.blogplatform.repository.BlogRepository;
import org.ashutosh.blogplatform.repository.CommentRepository;
import org.ashutosh.blogplatform.repository.UserRepository;
import org.ashutosh.blogplatform.service.CommentsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;


    @Override
    public CommentResponse addComment(Long blogId, CreateCommentRequest request) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(()-> new RuntimeException("Blog not Found"));

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Comment comment = Comment.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .blog(blog)
                .user(user)
                .build();

        commentRepository.save(comment);
        return mapToResponse(comment);
    }

    @Override
    public List<CommentResponse> getCommentsByBlog(Long blogId) {
        return commentRepository.findByBlogId(blogId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteComment(Long commentId) {

        Comment comment =
                commentRepository.findById(commentId)
                        .orElseThrow(() ->new RuntimeException(
                                        "Comment not found"));

        String currentUser =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        if(!comment.getUser()
                .getEmail()
                .equals(currentUser)) {

            throw new RuntimeException(
                    "Cannot delete others comments");
        }

        commentRepository.delete(comment);
    }

    private CommentResponse mapToResponse(
            Comment comment) {

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getUser().getName())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
