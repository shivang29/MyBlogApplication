package com.BlogApp.Application.controller;

import com.BlogApp.Application.dto.PostDto;
import com.BlogApp.Application.dto.ResetPasswordUser;
import com.BlogApp.Application.entity.Comments;
import com.BlogApp.Application.entity.Post;
import com.BlogApp.Application.security.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1.0/blog/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("add")
    public ResponseEntity createPost( @Valid @RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("domain/all")
    public ResponseEntity<List<PostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody String title) {
        return new ResponseEntity<>(postService.readSinglePost(title), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{title}")
    public boolean deleteBlog(@PathVariable @RequestBody String title) {
    	return postService.deleteBlogForTitle(title);
    }
    
    @PutMapping("/update")
    public Post updateBlog(@Valid @RequestBody Post post) {
    	return postService.updateBlogs(post);
    }
    
    @PostMapping("/reply/{title}")
    public Comments commentOnBlog( @PathVariable String title,@Valid @RequestBody Comments reply) {
    
    	return postService.addcomment(title,reply);
    }
    
    @GetMapping("/reply/get/{title}")
    public List<Comments> getComments(@PathVariable @RequestBody String title){
    	return postService.getComment(title);
    }
	
    @PutMapping("/like/{title}")
    public Post likepost(@PathVariable @RequestBody String title) {
    	return postService.likepost(title);
    }
	
}
