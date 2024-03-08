package com.firstPro.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer phno;
	private String address;
	private String gender;
	private List<Integer> follower = new ArrayList<>();
	private List<Integer> following = new ArrayList<>() ;
	
//	one user can save multiple posts and each post can be saved by multiple users.
//	Many users can save many posts and many posts can be saved by many users.
	@ManyToMany
	private List<Post>savedPost= new ArrayList<>();
	public User() {
		
	}
	

	public User(Integer id, String firstName, String lastName, String email, String password, Integer phno,
			String address, String gender, List<Integer> follower, List<Integer> following) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phno = phno;
		this.address = address;
		this.gender = gender;
		this.follower = follower;
		this.following = following;
	}

	

	public List<Post> getSavedPost() {
		return savedPost;
	}


	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPhno() {
		return phno;
	}

	public void setPhno(Integer phno) {
		this.phno = phno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public List<Integer> getFollower() {
		return follower;
	}


	public void setFollower(List<Integer> follower) {
		this.follower = follower;
	}


	public List<Integer> getFollowing() {
		return following;
	}


	public void setFollowing(List<Integer> following) {
		this.following = following;
	}
	
	

	
}
