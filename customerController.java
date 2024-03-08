package com.firstPro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.firstPro.models.customerSignUp;
import com.firstPro.service.customerService;

@Controller
@RequestMapping("/signup")
public class customerController {
	private customerService customerService;

	public customerController(com.firstPro.service.customerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@ModelAttribute("customer")
	public customerSignUp customerSignUp() {
		return new customerSignUp();
	}
	
	@GetMapping
	public String showSignUpForm() {
		return "signup";
	}

	@PostMapping
	public String signincustomerAccount(@ModelAttribute("customer") customerSignUp customerSignUp, RedirectAttributes redirectAttributes) {
	    customerService.signUp(customerSignUp);
	    redirectAttributes.addFlashAttribute("successMessage", "Your account has been successfully created!");
	    return "redirect:/signup";
	}


}
