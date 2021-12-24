package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Program;
import com.example.demo.repository.ProgramRepository;
import com.example.demo.service.ProgramService;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramRepository programRepository;

	@Override
	public List<Program> getAllPrograms() {
		return programRepository.findAll();
	}

	@Override
	public void saveProgram(Program program) {
		this.programRepository.save(program);
	}

	@Override
	public Program getProgramById(long id) {
		Optional<Program> optional = programRepository.findById(id);
		Program program = null;
		if (optional.isPresent()) {
			program = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return program;
	}

	@Override
	public void deleteProgramById(long id) {
		this.programRepository.deleteById(id);
	}

	@Override
	public Page<Program> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.programRepository.findAll(pageable);
	}
}
