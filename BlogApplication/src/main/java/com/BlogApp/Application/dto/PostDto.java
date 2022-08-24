package com.BlogApp.Application.dto;


import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PostDto {
 //   private String id;
	@NotBlank
	@Size(min = 2000, message = "Blog should have atleast 2000 words")
    private String content;
	@NotBlank
    private String title;
    private String username;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Long like;


    public Long getLike() {
		return like;
	}

	public void setLike(Long like) {
		this.like = like;
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

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
