package com.example.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.service.EmployeeDesignationService;

@RestController
@RequestMapping("api/v1")
public class EmployeeDesignationController {

	
	@Autowired
	private EmployeeDesignationService desService;
	
	
	@GetMapping("/empdesignation")
	public List<EmployeeDesignation> findAll(){
		return this.desService.findAll();
	}
	
	@PostMapping("/empdesignation")
	public EmployeeDesignation addDetails(EmployeeDesignation entity) {
		return this.desService.addDetails(entity);
	}
	
}
