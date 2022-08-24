package com.BlogApp.Application.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.BlogApp.Application.entity.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	Optional<Post> findByTitle(String title);
	boolean deleteByTitle(String title);
	
}
