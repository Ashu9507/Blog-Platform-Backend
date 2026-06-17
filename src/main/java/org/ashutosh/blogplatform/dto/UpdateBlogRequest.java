package org.ashutosh.blogplatform.dto;

import lombok.Data;

@Data
public class UpdateBlogRequest {

    private String title;
    private String content;
}
