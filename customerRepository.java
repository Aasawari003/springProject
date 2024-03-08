package com.firstPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstPro.models.customerSignUp;

public interface customerRepository extends JpaRepository<customerSignUp, Integer>{

	
}
