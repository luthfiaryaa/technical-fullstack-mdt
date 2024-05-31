package com.backend.dto;

import jakarta.validation.constraints.NotNull;

public class RegisterAccountDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public RegisterAccountDTO() {
    }

    public RegisterAccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
}
