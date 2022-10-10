package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmployeeDetails;
import com.example.demo.service.EmployeeDetailsService;

@RestController
@RequestMapping(path = "api/v1")
public class EmployeeDetailsController {
	
	@Autowired
	private EmployeeDetailsService service;

	@GetMapping(path = "/employees")
	public List<EmployeeDetails> findAllDetails(){
		return this.service.findAll();
	}
	
	@PostMapping(path = "/employees")
	public Object addDetail(@RequestBody EmployeeDetails entity) {
		return this.service.empDetails(entity);
		
	}
	
	@PutMapping(path = "/employees/update")
	public ResponseEntity<String> updateDetails(@RequestBody EmployeeDetails entity){
		String result=this.service.updateDeatils(entity);
		return ResponseEntity.ok().body(result);
	}
	
	
}
