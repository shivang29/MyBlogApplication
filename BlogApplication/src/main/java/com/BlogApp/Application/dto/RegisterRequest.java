package com.BlogApp.Application.dto;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class RegisterRequest {
	
	@NotNull
	private String name;
	@NotNull
	@Id
	private String username;
	@NotNull
	@Indexed(unique = true)
    private String email;
	@NotNull
    private String password;

	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
