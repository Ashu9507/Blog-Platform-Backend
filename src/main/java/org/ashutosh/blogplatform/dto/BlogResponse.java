package org.ashutosh.blogplatform.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlogResponse {

    private Long id;
    private String title;
    private String content;
    private String slug;
    private String author;
    private LocalDateTime createdAt;
}
