package org.ashutosh.blogplatform.service;

import org.ashutosh.blogplatform.dto.CommentResponse;
import org.ashutosh.blogplatform.dto.CreateCommentRequest;

import java.util.List;

public interface CommentsService {

    CommentResponse addComment(Long blogId, CreateCommentRequest request);

    List<CommentResponse> getCommentsByBlog(
            Long blogId);

    void deleteComment(Long commentId);
}
