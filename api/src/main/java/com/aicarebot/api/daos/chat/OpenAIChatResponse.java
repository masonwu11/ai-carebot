package com.aicarebot.api.daos.chat;

import java.util.List;

import lombok.Data;

@Data
public class OpenAIChatResponse {
    private long created;
    private List<OpenAIChoice> choices;
}
