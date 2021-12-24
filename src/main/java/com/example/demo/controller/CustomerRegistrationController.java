package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CustomerRegistrationDto;
import com.example.demo.model.Customer;
import com.example.demo.model.Program;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProgramService;

@Controller
@RequestMapping("/registration")
public class CustomerRegistrationController {
	
	@Autowired
	private CustomerService customerService;

	private CustomerService userService;

	public CustomerRegistrationController(CustomerService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public CustomerRegistrationDto userRegistrationDto() {
        return new CustomerRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") CustomerRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
//	@GetMapping("/showCustomerForUpdate/{id}")
//	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
//		
//		
//		Customer customer = customerService.getProgramById(id);
//		
//		
//		model.addAttribute("employee", employee);
//		return "osman";
//	}
}
