package com.aicarebot.api.daos;

import lombok.Data;

@Data
public class Request {
    private Long id;
    private String status;
    private String content;
    private String response;
}
