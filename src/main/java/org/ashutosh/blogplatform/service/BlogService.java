package org.ashutosh.blogplatform.service;

import org.ashutosh.blogplatform.dto.BlogResponse;
import org.ashutosh.blogplatform.dto.CreateBlogRequest;
import org.ashutosh.blogplatform.dto.UpdateBlogRequest;
import org.ashutosh.blogplatform.entity.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {

    BlogResponse createBlog(CreateBlogRequest request);

    Page<Blog> getAllBlogs(int page, int size, String sortBy);

    BlogResponse getBlogById(Long id);

    BlogResponse updateBlog(Long id, UpdateBlogRequest request);

    void deleteBlog(Long id);

    Page<BlogResponse> searchBlogs(String keyword, int page, int size);

    List<BlogResponse> getPopularBlogs();
}
