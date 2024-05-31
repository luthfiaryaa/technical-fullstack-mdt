package com.backend.dto;

public class RequestJwtDTO {
    private String username;
    private String password;
    private String subject;
    private String audience;
    private String secretKey;

    public RequestJwtDTO() {
    }

    public RequestJwtDTO(String username, String password, String subject, String audience, String secretKey) {
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.audience = audience;
        this.secretKey = secretKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
