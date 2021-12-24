package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Program;

public interface ProgramService {
	List<Program> getAllPrograms();
	void saveProgram(Program program);
	Program getProgramById(long id);
	void deleteProgramById(long id);
	Page<Program> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
