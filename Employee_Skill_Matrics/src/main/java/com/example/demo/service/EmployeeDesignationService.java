package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.repo.EmployeeDesignationRepo;

@Service
public class EmployeeDesignationService {

	
	@Autowired
	private EmployeeDesignationRepo desRepo;
	
	
	public List<EmployeeDesignation> findAll(){
		return this.desRepo.findAll();
	}
	
	public EmployeeDesignation addDetails(EmployeeDesignation entity) {
		return this.desRepo.save(entity);
	}
}
