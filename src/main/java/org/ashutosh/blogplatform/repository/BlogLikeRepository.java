package org.ashutosh.blogplatform.repository;

import org.ashutosh.blogplatform.entity.BlogLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogLikeRepository extends JpaRepository<BlogLike, Long> {

    Optional<BlogLike> findByUserIdAndBlogId(
            Long userId,
            Long blogId
    );

    long countByBlogId(Long blogId);

    boolean existsByUserIdAndBlogId(
            Long userId,
            Long blogId
    );

}
