package com.example.demo.service;


import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.repo.EmployeeDesignationRepo;
import com.example.demo.repo.EmployeeDetailsRepository;

@Service
public class EmployeeDetailsService {
	
	@Autowired
	private EmployeeDetailsRepository repo;
	
	@Autowired
	private EmployeeDesignationRepo desRepo;

	
	public Object empDetails(EmployeeDetails details) {
		 
//		  (details.getEmployeeDesignationId()==) {
//			  details.setDesignation("Manager");
//		  }
//		  else {
//			  details.setDesignation("Employee");
//		  }
//		return repo.save(details);
	
			
		  if(desRepo.existsById(details.getEmployeeDesignationId())) {
			  return repo.save(details);
		  }
			  return "Invalid id";	  
	}
	
	
	
	public List<EmployeeDetails> findAll(){
		return this.repo.findAll();
	}
	
	
	
	public String  updateDeatils(EmployeeDetails entity){
		

		 if(desRepo.existsById(entity.getEmployeeDesignationId())) {
			 int result=this.repo.updateEmployeeDetails(entity.getEmployeeName(), entity.getEmployeeDoj(), entity.getEmployeeDesignationId(), entity.getEmployeeId());
				if(result==1) {
					return "Updated";
				}
				else {
					return "Not updated";
				}
		  }
			  return "Invalid id";	  
	}
		
		
	}
	

