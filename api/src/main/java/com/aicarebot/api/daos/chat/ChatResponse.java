package com.aicarebot.api.daos.chat;

import java.util.List;

import lombok.Data;

@Data
public class ChatResponse {
    private long created;
    private List<Choice> choices;
}
