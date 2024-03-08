package com.firstPro.service;

import java.util.List;

import com.firstPro.exceptions.userException;
import com.firstPro.models.User;

public interface userService {
	
public User registerUser(User user); //for registering user

public User findUserById(Integer userId) throws userException; //for finding user by Id 

public User findUserByEmail(String email); //for finding user by Email

public User updateUser(User user,Integer userId) throws userException;//for updating user

public User followUser(Integer userId1,Integer userId2) throws userException;

public List<User>searchUser(String query);//for searching user with anything(name/firstN/lastN/email)

public String deleteUser(Integer usedId); //for deleting user with id

public User findUserByJwt(String jwt); //to find user with token 

}
