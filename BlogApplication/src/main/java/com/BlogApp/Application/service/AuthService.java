package com.BlogApp.Application.service;
import com.BlogApp.Application.dto.LoginRequest;
import com.BlogApp.Application.dto.RegisterRequest;
import com.BlogApp.Application.dto.ResetPasswordUser;
import com.BlogApp.Application.entity.User;
import com.BlogApp.Application.exception.SpringBlogException;
import com.BlogApp.Application.repository.UserRepository;
import com.BlogApp.Application.security.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

   public void signup(RegisterRequest registerRequest) {
       User user = new User();   
      //  It is used to show if any empty element in register to not store into database
        if(registerRequest.getEmail() == "" || registerRequest.getUsername() == "" ||  registerRequest.getName() == ""|| registerRequest.getPassword() == "" ) {
        	return;
        }
        user.setName(registerRequest.getName());
       user.setUserName(registerRequest.getUsername());
       user.setEmail(registerRequest.getEmail());
       user.setPassword(encodePassword(registerRequest.getPassword()));      	
         userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    
    public AuthenticationResponse login(LoginRequest loginRequest) throws SpringBlogException {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String authenticationToken = jwtProvider.generateToken(authenticate);
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setAuthenticationToken(authenticationToken);
		authResponse.setUsername(loginRequest.getUsername());
        //return  AuthenticationResponse(authenticationToken, loginRequest.getUsername());
		System.out.println("*************"+authResponse.getAuthenticationToken() + " " + authResponse.getUsername());
		return authResponse;
	}
//    public String login(LoginRequest loginRequest) {
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//                loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//       // @SuppressWarnings("unused")
//        return  jwtProvider.generateToken(authenticate);
//	
//    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

	public User resetPassword(ResetPasswordUser resetUser) {
		// TODO Auto-generated method stub
		User blogger = userRepository.findByUserName(resetUser.getUsername()).orElseThrow(() ->
        new UsernameNotFoundException("No user found " ));
		
		blogger.setPassword(encodePassword(resetUser.getPassword()));
		
		userRepository.save(blogger);
		return blogger;
		
	}

    
    
}
