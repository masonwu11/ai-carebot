package com.aicarebot.api.daos.chat;

import lombok.Data;

@Data
public class ChatRecord {
  private Long id;
  private Long userId;
  private String messages; // JSON string
}