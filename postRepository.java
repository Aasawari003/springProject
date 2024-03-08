package com.firstPro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.firstPro.models.Post;

public interface postRepository extends JpaRepository<Post, Integer> {

	@Query("select p from Post p where p.user.id=:userId")
	List<Post>findPostByUserId(Integer userId);
}
