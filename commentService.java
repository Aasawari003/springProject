package com.firstPro.service;

import com.firstPro.models.Comment;

public interface commentService {

	public Comment createComment(
			Comment comment,
			Integer postId,
			Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer CommentId,Integer userId) throws Exception;
	
}
