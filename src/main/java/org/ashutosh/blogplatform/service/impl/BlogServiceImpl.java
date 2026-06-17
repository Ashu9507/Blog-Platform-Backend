package org.ashutosh.blogplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.BlogResponse;
import org.ashutosh.blogplatform.dto.CreateBlogRequest;
import org.ashutosh.blogplatform.dto.UpdateBlogRequest;
import org.ashutosh.blogplatform.entity.Blog;
import org.ashutosh.blogplatform.entity.User;
import org.ashutosh.blogplatform.repository.BlogRepository;
import org.ashutosh.blogplatform.repository.UserRepository;
import org.ashutosh.blogplatform.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Override
    public BlogResponse createBlog(CreateBlogRequest request) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Blog blog = Blog.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .slug(genearteSlug(request.getTitle()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .author(user)
                .build();

        blogRepository.save(blog);

        return mapToResponse(blog);
    }

    private BlogResponse mapToResponse(Blog blog) {

        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .slug(blog.getSlug())
                .author(blog.getAuthor().getName())
                .createdAt(blog.getCreatedAt())
                .build();
    }

    private String genearteSlug(String title) {

        return title.toLowerCase()
                .replace(" ","-");
    }

    @Override
    public Page<Blog> getAllBlogs(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(sortBy).descending());

        return blogRepository.findAll(pageable);
    }

    @Override
    public BlogResponse getBlogById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Blog not found"));
        return mapToResponse(blog);
    }

    @Override
    public BlogResponse updateBlog(Long id, UpdateBlogRequest request) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Blog not found"));

        validateOwnership(blog);

        blog.setUpdatedAt(LocalDateTime.now());
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());

        return mapToResponse(blog);
    }

    private void validateOwnership(Blog blog) {

        String currentUser = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        if (!blog.getAuthor().getEmail().equals(currentUser)) {
            throw new RuntimeException("You can modify only your own blogs");
        }
    }

    @Override
    public void deleteBlog(Long id) {

        Blog blog = blogRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Blog not found"));

        validateOwnership(blog);

        blogRepository.delete(blog);

    }

    @Override
    public Page<BlogResponse> searchBlogs(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable)
                .map(this::mapToResponse);


    }

    @Override
    public List<BlogResponse> getPopularBlogs() {
        return blogRepository.findPopularBlogs()
                .stream()
                .limit(10)
                .map(this::mapToResponse)
                .toList();
    }
}
