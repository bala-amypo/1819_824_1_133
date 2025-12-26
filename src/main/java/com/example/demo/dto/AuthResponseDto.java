package com.example.demo.dto;

public class AuthResponseDto {
    private String token;
    private long expiresAt;

    public AuthResponseDto() {}

    public AuthResponseDto(String token, long expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public long getExpiresAt() { return expiresAt; }
    public void setExpiresAt(long expiresAt) { this.expiresAt = expiresAt; }
}