package com.firstPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstPro.models.Comment;

public interface commentRepository extends JpaRepository<Comment, Integer>{
	
	

}
