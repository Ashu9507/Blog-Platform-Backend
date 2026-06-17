package org.ashutosh.blogplatform.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreateBlogRequest {

    private String title;
    private String content;
    private String category;
    private Set<String> tags;
}
