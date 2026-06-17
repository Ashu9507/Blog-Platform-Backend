package org.ashutosh.blogplatform.repository;

import org.ashutosh.blogplatform.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable);

    Page<Blog> findByCategory_Name(
            String category,
            Pageable pageable);

    Page<Blog> findByTags_Name(
            String tag,
            Pageable pageable);

    @Query("""
            SELECT b
            FROM Blog b
            LEFT JOIN b.likes l
            GROUP BY b
            ORDER BY COUNT(l) DESC
            """)
    List<Blog> findPopularBlogs();
}
