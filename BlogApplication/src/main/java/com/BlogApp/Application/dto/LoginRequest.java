package com.BlogApp.Application.dto;

import javax.validation.constraints.NotNull;

public class LoginRequest {
	@NotNull
    private String username;
	@NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
