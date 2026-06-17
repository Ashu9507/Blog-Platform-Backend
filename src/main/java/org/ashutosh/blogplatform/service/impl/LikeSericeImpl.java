package org.ashutosh.blogplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.LikeResponse;
import org.ashutosh.blogplatform.entity.Blog;
import org.ashutosh.blogplatform.entity.BlogLike;
import org.ashutosh.blogplatform.entity.User;
import org.ashutosh.blogplatform.repository.BlogLikeRepository;
import org.ashutosh.blogplatform.repository.BlogRepository;
import org.ashutosh.blogplatform.repository.UserRepository;
import org.ashutosh.blogplatform.service.LikeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LikeSericeImpl implements LikeService {

    private final BlogLikeRepository blogLikeRepository;
    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    @Override
    public LikeResponse likeBlog(Long blogId) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Blog blog = blogRepository.findById(blogId)
                .orElseThrow();

        boolean alreadyLiked = blogLikeRepository.existsByUserIdAndBlogId(user.getId(), blogId);
        if(!alreadyLiked) {
            BlogLike like = BlogLike.builder()
                    .user(user)
                    .blog(blog)
                    .createdAt(LocalDateTime.now())
                    .build();

            blogLikeRepository.save(like);
        }
        return buildResponse(blogId, user.getId());
    }

    private LikeResponse buildResponse(Long blogId, Long id) {

        return LikeResponse.builder()
                .blogId(blogId)
                .totalLikes(blogLikeRepository.countByBlogId(blogId))
                .likedByCurrentUser(blogLikeRepository.existsByUserIdAndBlogId(id, blogId))
                .build();
    }

    @Override
    public LikeResponse unlikeBlog(Long blogId) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        blogLikeRepository.findByUserIdAndBlogId(user.getId(), blogId)
                .ifPresent(blogLikeRepository::delete);

        return buildResponse(blogId, user.getId());
    }

    @Override
    public LikeResponse getLikes(Long blogId) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return buildResponse(blogId, user.getId());
    }
}
