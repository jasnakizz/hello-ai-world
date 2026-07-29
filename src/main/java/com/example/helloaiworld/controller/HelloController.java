package com.example.helloaiworld.controller;

import com.example.helloaiworld.dto.HelloRequest;
import com.example.helloaiworld.dto.HelloResponse;
import com.example.helloaiworld.service.AnthropicChatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {

    private final AnthropicChatService anthropicChatService;

    public HelloController(AnthropicChatService anthropicChatService) {
        this.anthropicChatService = anthropicChatService;
    }

    @PostMapping("/hello")
    public HelloResponse hello(@RequestBody HelloRequest request) {
        String aiResponse = anthropicChatService.sendMessage(request.message());
        return new HelloResponse(aiResponse);
    }
}
