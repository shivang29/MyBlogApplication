package com.BlogApp.Application.entity;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;


@Document(collection = "post")
public class Post {
    
   
    @NotBlank
    @Id
    private String title;
    @NotBlank
    @Size(min = 2000)
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    @NotBlank
    private String username;
    
    private Long like;


    public Long getLike() {
		return like;
	}

	public void setLike(Long like) {
		this.like = like;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
