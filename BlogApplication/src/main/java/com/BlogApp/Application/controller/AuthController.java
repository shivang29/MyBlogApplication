package com.BlogApp.Application.controller;

import com.BlogApp.Application.dto.LoginRequest;
import com.BlogApp.Application.dto.RegisterRequest;
import com.BlogApp.Application.dto.ResetPasswordUser;
import com.BlogApp.Application.entity.User;
import com.BlogApp.Application.exception.SpringBlogException;
import com.BlogApp.Application.service.AuthService;
import com.BlogApp.Application.service.AuthenticationResponse;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1.0/blog")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
	@PostMapping("/register")
    public ResponseEntity signup(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
    	
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) throws SpringBlogException {
    	
    	return authService.login(loginRequest);		
		
	} 
    
    @PutMapping("/forget")
    public User resetPassword(@RequestBody ResetPasswordUser resetUser ) {
		
    	return authService.resetPassword( resetUser);
    	
    	//return resetUser;
    	 
    }

    	
    
    
    
}
