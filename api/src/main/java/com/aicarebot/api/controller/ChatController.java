package com.aicarebot.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aicarebot.api.services.ChatService;


@RestController
public class ChatController {
    private final ChatService chatService;
    
    public ChatController(ChatService openAIService) {
        this.chatService = openAIService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String prompt) {
        String response = chatService.chat(prompt);

        return ResponseEntity.ok(response);
    }
    
}
