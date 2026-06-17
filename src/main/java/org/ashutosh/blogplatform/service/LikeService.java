package org.ashutosh.blogplatform.service;

import org.ashutosh.blogplatform.dto.LikeResponse;

public interface LikeService {

    LikeResponse likeBlog(Long blogId);

    LikeResponse unlikeBlog(Long blogId);

    LikeResponse getLikes(Long blogId);
}
