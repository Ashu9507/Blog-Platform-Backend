package org.ashutosh.blogplatform.controller;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.BlogResponse;
import org.ashutosh.blogplatform.dto.CreateBlogRequest;
import org.ashutosh.blogplatform.dto.UpdateBlogRequest;
import org.ashutosh.blogplatform.entity.Blog;
import org.ashutosh.blogplatform.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(@RequestBody CreateBlogRequest request) {

        return ResponseEntity.ok(blogService.createBlog(request));
    }

    @GetMapping
    public ResponseEntity<Page<Blog>>
    getAllBlogs(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {

        return ResponseEntity.ok(
                blogService.getAllBlogs(
                        page,
                        size,
                        sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long id) {

        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(@PathVariable Long id, @RequestBody UpdateBlogRequest request) {

        return ResponseEntity.ok(
                blogService.updateBlog(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(
            @PathVariable Long id) {

        blogService.deleteBlog(id);

        return ResponseEntity.ok(
                "Blog deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BlogResponse>>
    searchBlogs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size) {

        return ResponseEntity.ok(
                blogService.searchBlogs(
                        keyword,
                        page,
                        size));
    }

    @GetMapping("/popular")
    public List<BlogResponse> popularBlogs() {
        return blogService.getPopularBlogs();
    }

}
