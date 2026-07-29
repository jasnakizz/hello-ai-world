package com.example.helloaiworld.service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.TextBlock;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AnthropicChatService {

    private static final String MODEL = "claude-haiku-4-5";

    private final AnthropicClient client;

    public AnthropicChatService() {
        this.client = AnthropicOkHttpClient.fromEnv();
    }

    public String sendMessage(String userMessage) {
        MessageCreateParams params = MessageCreateParams.builder()
                .model(MODEL)
                .maxTokens(1024L)
                .addUserMessage(userMessage)
                .build();

        Message response = client.messages().create(params);

        return response.content().stream()
                .flatMap(block -> block.text().stream())
                .map(TextBlock::text)
                .collect(Collectors.joining());
    }
}
