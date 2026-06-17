package org.ashutosh.blogplatform.service;

public interface AIService {

    String generateBlog(String topic);

    String generateTitles(String topic);

    String summarizeBlog(String content);

    String generateSeoTags(String content);
}
