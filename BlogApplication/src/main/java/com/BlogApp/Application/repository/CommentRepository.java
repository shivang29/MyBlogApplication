package com.BlogApp.Application.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.BlogApp.Application.entity.Comments;

public interface CommentRepository extends MongoRepository<Comments, String> {
	

	List<Comments> findByTitle(String title);
}
