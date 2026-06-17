package org.ashutosh.blogplatform.controller;

import lombok.RequiredArgsConstructor;
import org.ashutosh.blogplatform.dto.AIResponse;
import org.ashutosh.blogplatform.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    @PostMapping("/generate-blog")
    public AIResponse generateblog(@RequestParam String topic) {
        return new AIResponse(aiService.generateBlog(topic));
    }

    @PostMapping("/generate-title")
    public AIResponse generateTitle(
            @RequestParam String topic) {

        return new AIResponse(aiService.generateTitles(topic));
    }

    @PostMapping("/summarize")
    public AIResponse summarize(
            @RequestBody String content) {

        return new AIResponse(aiService.summarizeBlog(content));
    }

    @PostMapping("/seo-tags")
    public AIResponse seoTags(
            @RequestBody String content) {

        return new AIResponse(aiService.generateSeoTags(content));
    }
}
