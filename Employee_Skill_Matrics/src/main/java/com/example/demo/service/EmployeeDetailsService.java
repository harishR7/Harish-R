package com.example.demo.service;


import java.security.cert.PKIXRevocationChecker.Option;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.TransactionLog;
import com.example.demo.repo.EmployeeDesignationRepo;
import com.example.demo.repo.EmployeeDetailsRepository;
import com.example.demo.repo.TransactionLogRepo;

@Service
public class EmployeeDetailsService {
	
	@Autowired
	private EmployeeDetailsRepository repo;
	
	@Autowired
	private EmployeeDesignationRepo desRepo;
	@Autowired
	private TransactionLogRepo transRepo;

	
	public Object empDetails(EmployeeDetails details) {
		 
//		  (details.getEmployeeDesignationId()==) {
//			  details.setDesignation("Manager");
//		  }
//		  else {
//			  details.setDesignation("Employee");
//		  }
//		return repo.save(details);
	
		TransactionLog transaction=new TransactionLog();
		transaction.setApiOperationName("EmployeeDetailsAreBeenAdded");
		transaction.setCreatedDate(Date.valueOf(LocalDate.now()));
		transaction.setRequest(details.toString());
		transaction.setCreatedTime(new SimpleDateFormat("HH.mm aa").format(new java.util.Date()));
			
		
		  if(desRepo.existsById(details.getEmployeeDesignationId())) {
			  transaction.setResponse(details.toString());
			  return repo.save(details);
		  }
			  return "Invalid id";	  
	}
	
	
	
	public List<EmployeeDetails> findAll(){
		return this.repo.findAll();
	}
	
	
	
	public String  updateDeatils(EmployeeDetails entity){
		
		TransactionLog transaction=new TransactionLog();
		transaction.setApiOperationName("EmployeeDetailsAreBeenUpdate");
		transaction.setCreatedDate(Date.valueOf(LocalDate.now()));
		transaction.setRequest(entity.toString());
		transaction.setCreatedTime(new SimpleDateFormat("HH.mm aa").format(new java.util.Date()));
		
		

		 if(desRepo.existsById(entity.getEmployeeDesignationId())) {
			 int result=this.repo.updateEmployeeDetails(entity.getEmployeeName(), entity.getEmployeeDoj(), entity.getEmployeeDesignationId(), entity.getEmployeeId());
				if(result==1) {
					transaction.setResponse("Updated");
					transRepo.save(transaction);
					return "Updated";
				}
				else {
					transaction.setResponse("Not Updated");
					transRepo.save(transaction);
					return "Not updated";
				}
		  }
		 transaction.setResponse("Invalid Id");
		 transRepo.save(transaction);
			  return "Invalid id";	
			  
	}
		
		
	}
	

