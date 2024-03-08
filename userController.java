package com.firstPro.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firstPro.exceptions.userException;
import com.firstPro.models.User;
import com.firstPro.repository.userRepository;
import com.firstPro.service.userService;
@RestController
public class userController {
	@Autowired
	userRepository userRepository;
	@Autowired
	userService userService;
	
//for entering users

	
//for getting all users
@GetMapping("/api/users")
public List<User> getUsers(){
	List<User> users=userRepository.findAll();
	return users;
}
//find users by id
@GetMapping("/api/users/{userId}")
public User getUserById(@PathVariable("userId")Integer id)throws userException{
	User user = userService.findUserById(id);
	return user;
}
//update user
@PutMapping("/api/users")
public User updateUser(@RequestHeader("Authorization")String jwt
		,@RequestBody User user) throws userException {
	
//	below the reqUser is logged in user so as we know without token we cannot see or not even 
//	able to  update anything and to get token we need pwd and 
//	username so no other user can use 
//	token of others for login to another account.
//	this enhances the security
	User reqUser = userService.findUserByJwt(jwt);
	User updatedUser = userService.updateUser(user, reqUser.getId());
	return updatedUser;
}
//search user
@GetMapping("/api/users/search")
public List<User>searchUser(@RequestParam("query")String query){
	List<User>users=userService.searchUser(query);
	return users;
}
@DeleteMapping("/api/users/{userId}")
public String deleteUser(@PathVariable Integer userId) throws userException{
return userService.deleteUser(userId);
}


//to get the profile of user(where we need to provide individual record Token) we used following profile method
	@GetMapping("/api/users/profile")   // we will secure this endpoint with api bcz we need jwt to get token
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
	
	@PutMapping("/api/users/follow/{userId1}/{userId2}")
	public User followUserHandler(@PathVariable Integer userId1,@PathVariable Integer userId2) throws userException {
		User user=userService.followUser(userId1, userId2);
		return user ;
}
}