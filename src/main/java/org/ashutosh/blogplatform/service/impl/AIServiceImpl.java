package org.ashutosh.blogplatform.service.impl;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.service.AIService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final ChatClient.Builder chatClient;

    @Override
    public String generateBlog(String topic) {
        return chatClient.build()
                .prompt("""
                        Write a professional blog article about:
                        %s
                        Requirements:
                        -Introduction
                        -Headings
                        -Conclusion
                        -500 words
                        """
                .formatted(topic))
                .call()
                .content();
    }

    @Override
    public String generateTitles(String topic) {
        return chatClient.build()
                .prompt("""
                        Generate 5 SEO friendly blog titles for:
                        
                        %s
                        """
                        .formatted(topic))
                .call()
                .content();
    }

    @Override
    public String summarizeBlog(String content) {
        return chatClient.build()
                .prompt("""
                        Summarize the following blog in 3 sentences:
                        
                        %s
                        """
                        .formatted(content))
                .call()
                .content();
    }

    @Override
    public String generateSeoTags(String content) {
        return chatClient.build()
                .prompt("""
                        Generate 10 SEO tags from:
                        
                        %s
                        """
                        .formatted(content))
                .call()
                .content();
    }
}
