package com.firstPro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstPro.config.JwtProvider;
import com.firstPro.models.User;
import com.firstPro.repository.userRepository;
import com.firstPro.request.LoginRequest;
import com.firstPro.response.AuthResponse;
import com.firstPro.service.customerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private  com.firstPro.service.userService userService;
	
	@Autowired
	private userRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private customerUserDetailsService customerUserDetailsService;
	
//	 /auth/signup

	 @PostMapping("/signup")
	 public AuthResponse createUser(@RequestBody User user) throws Exception {
		 
		 User isExist = userRepository.findByEmail(user.getEmail());
		 if (isExist!=null) {
			 throw new Exception("This Email Already Used with another Account");
			
		}
			User newUser=new User();
//			newUser.setId(user.getId()); as id autogenerated hogi
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setEmail(user.getEmail());
			newUser.setPassword(passwordEncoder.encode(user.getPassword()));
			newUser.setPhno(user.getPhno());
			newUser.setAddress(user.getAddress());
			newUser.setGender(user.getGender());
			
			User savedUser=userRepository.save(newUser);
			
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(savedUser.getEmail() , savedUser.getPassword());
			
			String token = JwtProvider.generateToken(authentication);
			AuthResponse res = new AuthResponse(token,"Register Successful");
			return res;
	}
	 
	 //now for signin/login
	 
//	 /auth/signin
	 @PostMapping("/signin")
	 public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		 
		 Authentication authentication = 
				 authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		 
		 String token = JwtProvider.generateToken(authentication);
		 
			AuthResponse res = new AuthResponse(token,"Login Successful");
			return res;
	 }

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
		if (userDetails==null) {
			throw new BadCredentialsException("Invalid Username");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
			//in above password = obtained through interface and  2nd arugment is password obtained through backend which will be encoded
		}
		return new UsernamePasswordAuthenticationToken(userDetails,
				null,
				userDetails.getAuthorities());
	}
}
