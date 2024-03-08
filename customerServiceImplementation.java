package com.firstPro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstPro.models.customerSignUp;
import com.firstPro.repository.customerRepository;

@Service
public class customerServiceImplementation implements customerService {

	@Autowired
	private customerRepository cRepository;
//	
//	
//	public customerServiceImplementation(customerRepository cRepository) {
//		super();
//		this.cRepository = cRepository;
//	}

	@Override
	public customerSignUp signUp(customerSignUp cUp) {
		
		customerSignUp customerSignUp=new customerSignUp();
		customerSignUp.setId(customerSignUp.getId());
		customerSignUp.setUsername(customerSignUp.getUsername());
		customerSignUp.setEmail(customerSignUp.getEmail());
		customerSignUp.setPassword(customerSignUp.getPassword());
		customerSignUp.setCpassword(customerSignUp.getCpassword());
		return cRepository.save(cUp);
		
	}

}
