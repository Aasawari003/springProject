package com.firstPro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstPro.config.JwtProvider;
import com.firstPro.exceptions.userException;
import com.firstPro.models.User;
import com.firstPro.repository.userRepository;

@Service
public class userServiceImplementation implements userService {

	@Autowired
	userRepository userRepository; 
	@Override
	public User registerUser(User user) {

		User newUser=new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setPhno(user.getPhno());
		newUser.setAddress(user.getAddress());
		newUser.setGender(user.getGender());
		newUser.setFollower(user.getFollower());
		newUser.setFollowing(user.getFollowing());
		User savedUser=userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws userException {

		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new userException("user does not exist with userid"+userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User updateUser(User user,Integer userId) throws userException {

		Optional<User> user1=userRepository.findById(userId);
		
		if (user1.isEmpty()) {
			throw new userException("user does not exists with id"+userId);
			
		}
	User oldUser=user1.get();
	if (user.getFirstName()!=null) {
		oldUser.setFirstName(user.getFirstName());
	}
	if (user.getLastName()!=null) {
		oldUser.setLastName(user.getLastName());
	}
	if (user.getEmail()!=null) {
		oldUser.setEmail(user.getEmail());
	}
	if (user.getAddress()!=null) {
		oldUser.setAddress(user.getAddress());
	}
	if (user.getPhno()!=null) {
		oldUser.setPhno(user.getPhno());
	}
	
	if (user.getGender()!=null) {
		oldUser.setGender(user.getGender());
		
	}
	
	User updatedUser=userRepository.save(oldUser);
	return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {

		return userRepository.searchUser(query);
	}
	@Override
	public String deleteUser(Integer usedId) {
		if (userRepository.existsById(usedId)) {
			userRepository.deleteById(usedId);
			return "User Deleted with Id "+usedId;
		}
		else {
			return "No such Id present"; 
		}
		
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws userException {
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		user2.getFollower().add(user1.getId());
		user1.getFollowing().add(user2.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		return user1;
		}
}
