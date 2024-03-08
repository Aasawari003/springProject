package com.firstPro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.firstPro.models.Comment;
import com.firstPro.models.User;
import com.firstPro.service.commentService;
import com.firstPro.service.userService;

@RestController
public class commentController {

	
	@Autowired
	private commentService commentService;
	
	@Autowired
	private userService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment,
			@RequestHeader("Authorization") String jwt,
			@PathVariable("postId") Integer postId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		Comment Createdcomment = commentService.createComment(comment,
				postId,
				user.getId());
	
				return Createdcomment;	
	}
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		Comment likedcomment = commentService.likeComment(commentId,
				user.getId());
	
				return likedcomment;	
	}
}
