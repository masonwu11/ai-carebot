package com.aicarebot.api.daos;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
