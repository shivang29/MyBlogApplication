package com.BlogApp.Application.security;

import com.BlogApp.Application.dto.PostDto;
import com.BlogApp.Application.entity.Comments;
import com.BlogApp.Application.entity.Post;
import com.BlogApp.Application.exception.PostNotFoundException;
import com.BlogApp.Application.repository.CommentRepository;
import com.BlogApp.Application.repository.PostRepository;
import com.BlogApp.Application.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepo;

    @Transactional
    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    @Transactional
    public void createPost(PostDto postDto) {
        Post post = mapFromDtoToPost(postDto);
        if(post.getTitle() == "" || post.getContent() == "") {
        	return;
        }
        postRepository.save(post);
    }

    @Transactional
    public PostDto readSinglePost(String title) {
        Post post = postRepository.findByTitle(title).orElseThrow(() -> new PostNotFoundException("For id " + title));
        return mapFromPostToDto(post);
    }
    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        postDto.setCreatedOn(post.getCreatedOn());
        postDto.setUpdatedOn(post.getUpdatedOn());
        postDto.setLike(post.getLike());
        return postDto;
    }

    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(LocalDateTime.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(LocalDateTime.now());
        post.setLike((long) 0);
        return post;
    }
    
    
    public boolean deleteBlogForTitle(String title) {
 	   Post post = postRepository.findByTitle(title).orElseThrow(() -> new PostNotFoundException("For id " + title));
 	   User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
 	   int a = post.getUsername().length();
 	   int b =loggedInUser.getUsername().length();
 	   if(a == b ) {
 		   postRepository.delete(post);
 		   return true;
 	   }
 	   return false;
 	 
 }	
public Post updateBlogs(Post post) {
		
		User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
		Post existingBlog = postRepository.findByTitle(post.getTitle()).orElseThrow(() -> new PostNotFoundException("For id " + post)); 	   
		existingBlog.setTitle(post.getTitle());
		existingBlog.setContent(post.getContent());
		existingBlog.setUpdatedOn(LocalDateTime.now());
		postRepository.save(existingBlog);
		return existingBlog;
	}

	public Comments addcomment(String title, Comments reply) {
	// TODO Auto-generated method stub
		User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
	
			reply.setTitle(title);
			reply.setUsername(loggedInUser.getUsername());
	
			commentRepo.save(reply);
			return reply;
}

	public List<Comments> getComment(String title) {
		// TODO Auto-generated method stub
		List<Comments> allcomment =commentRepo.findByTitle(title);
		return allcomment;
	}

	public Post likepost(String title) {
		// TODO Auto-generated method stub
		User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
		Post existingBlog = postRepository.findByTitle(title).orElseThrow(() -> new PostNotFoundException("For id " + title)); 
		existingBlog.setLike(existingBlog.getLike()+1);
		postRepository.save(existingBlog);
		return existingBlog;
	}
   

}
