package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.SimpleFormatter;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.jpa.convert.threetenbp.ThreeTenBackPortJpaConverters.LocalTimeConverter;
import org.springframework.stereotype.Service;


import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.SkillReference;
import com.example.demo.entity.TransactionLog;
import com.example.demo.repo.EmployeeDesignationRepo;
import com.example.demo.repo.EmployeeDetailsRepository;

import com.example.demo.repo.SkillReferenceRepo;
import com.example.demo.repo.TransactionLogRepo;

import static java.util.stream.Collectors.*;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.time.LocalTime;



@Service
public class SkillReferenceService {

	
	@Autowired
	private SkillReferenceRepo skillrepo;
	
	@Autowired
	private EmployeeDetailsRepository detailrepo;
	
	@Autowired
	private EmployeeDesignationRepo desRepo;
	
	@Autowired
	private TransactionLogRepo transRepo;

	private List<String> managerName;
	
	
	public SkillReferenceService(List<String> managerName) {
		super();
		this.managerName = managerName;
	}

	public List<SkillReference> findAll(){
		return this.skillrepo.findAll();
	}

	
    // create skillDetails by manager
	public Object addSkill(SkillReference entity) {
		
		TransactionLog transaction=new TransactionLog();
		transaction.setApiOperationName("SKillRefDetailsAreBeenAdded");
		transaction.setCreatedDate(Date.valueOf(LocalDate.now()));
		transaction.setRequest(entity.toString());
		transaction.setCreatedTime(new SimpleDateFormat("HH.mm aa").format(new java.util.Date()));
		
		
		
		
		
		
		entity.setCreatedDate(Date.valueOf(LocalDate.now()));
		entity.setCreatedTime(new SimpleDateFormat("HH.mm aa").format(new java.util.Date()));
		int managerId=desRepo.findByDesignation("Manager").getEmployeeDesignationId();
		List<EmployeeDetails> managerDetails= this.detailrepo.findByEmployeeDesignationId(managerId);
		//managerDetails.forEach(System.out::println);
	     managerName=managerDetails.stream().map((e) ->e.getEmployeeName()).collect(toList());
	  //   managerName.forEach(System.out::println);
	     if(managerName.contains(entity.getCreatedBy())){
	     
	     Optional<SkillReference> skillDetail=this.skillrepo.findBySkillName(entity.getSkillName());
	  
	     if(skillDetail.isPresent()) {
	    	 transaction.setResponse("SkillName Already Exsist");
	    	 transRepo.save(transaction);
	    	return "SkillName Already Exsist";
	     }
	     else {
	    	 transaction.setResponse(entity.toString());
	    	 transRepo.save(transaction);
	    	 return  this.skillrepo.save(entity);
		}
	     }
		else {
			transaction.setResponse("Unauthoried Name");
			transRepo.save(transaction);
			return "Unauthoried Name";
		}	
	}
	
	
}
