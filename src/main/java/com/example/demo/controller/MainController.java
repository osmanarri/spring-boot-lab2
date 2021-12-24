package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Program;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	/*
	 * @GetMapping("/") public String home() { return "index"; }
	 */
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
		Program program = new Program();
		model.addAttribute("program", program);
		
		  final ArrayList<Integer> prices = new ArrayList<Integer>();
			 
	        prices.add(10);
	        prices.add(20);
	        prices.add(50);
//	        prices.add(60);	
			

	        model.addAttribute("prices", prices);
		
		return "checkout";
	}
	@GetMapping("/result")
	public String result() {
		
		return "result";
	}
}
