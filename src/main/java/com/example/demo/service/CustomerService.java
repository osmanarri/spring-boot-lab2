package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.dto.CustomerRegistrationDto;
import com.example.demo.model.Customer;

public interface CustomerService extends UserDetailsService{
	Customer save(CustomerRegistrationDto registrationDto);
}
