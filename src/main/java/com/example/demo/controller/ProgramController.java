package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Program;
import com.example.demo.service.ProgramService;

@Controller
public class ProgramController {
	


	@Autowired
	private ProgramService programService;
	
	// display list of programs
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "programName", "asc", model);		
	}
	
	@GetMapping("/showNewProgramForm")
	public String showNewProgramForm(Model model) {
		// create model attribute to bind form data
		Program program = new Program();
		model.addAttribute("program", program);
		
		final ArrayList<String> programList = new ArrayList<String>();
		 
			programList.add("Yoga");
			programList.add("Swimming");
			programList.add("Bootcamp");
			programList.add("Cardio");	
			

        model.addAttribute("programList", programList);
        //------------------
    	
		 
  
      
        
		
		return "new_program";
	}
	
	@PostMapping("/saveProgram")
	public String saveProgram(@ModelAttribute("program") Program program) {
		// save program to database
		programService.saveProgram(program);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get program from the service
		Program program = programService.getProgramById(id);
		
		// set program as a model attribute to pre-populate the form
		model.addAttribute("program", program);
		
		final ArrayList<String> programList = new ArrayList<String>();
		 
		programList.add("Yoga");
		programList.add("Swimming");
		programList.add("Bootcamp");
		programList.add("Cardio");	
		

    model.addAttribute("programList", programList);
		
		return "update_program";
	}
	
	@GetMapping("/deleteProgram/{id}")
	public String deleteProgram(@PathVariable (value = "id") long id) {
		
		// call delete prgram method 
		this.programService.deleteProgramById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {		
		
		int pageSize = 5;
		
		Page<Program> page = programService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Program> listPrograms = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPrograms", listPrograms);
		
		final ArrayList<String> duration = new ArrayList<String>();
		
	  	duration.add("One week");
    	duration.add("Two weeks");
    	duration.add("Three weeks");
    	duration.add("Four weeks");			

        model.addAttribute("duration", duration);
        
		return "index";
	}
	

}
