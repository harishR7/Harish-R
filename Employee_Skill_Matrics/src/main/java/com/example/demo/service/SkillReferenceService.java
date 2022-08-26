package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.SkillReference;
import com.example.demo.repo.EmployeeDesignationRepo;
import com.example.demo.repo.EmployeeDetailsRepository;
import com.example.demo.repo.EmployeeSkillRepo;
import com.example.demo.repo.SkillReferenceRepo;

import static java.util.stream.Collectors.*;



@Service
public class SkillReferenceService {

	
	@Autowired
	private SkillReferenceRepo skillrepo;
	
	@Autowired
	private EmployeeDetailsRepository detailrepo;
	
	@Autowired
	private EmployeeDesignationRepo desRepo;

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
		int managerId=desRepo.findByDesignation("Manager").getEmployeeDesignationId();
		List<EmployeeDetails> managerDetails= this.detailrepo.findByEmployeeDesignationId(managerId);
	     managerName=managerDetails.stream().map((e) ->e.getEmployeeName()).collect(toList());
		
	     if(managerName.contains(entity.getCreatedBy())){
	     
	     Optional<SkillReference> skillDetail=this.skillrepo.findBySkillName(entity.getSkillName());
	  
	     if(skillDetail.isPresent()) {
	    	
	    	return "SkillName Already Exsist";
	     }
	     else {
		 
	    	 return  this.skillrepo.save(entity);
		}
	     }
		else {
			return "Unauthoried Name";
		}	
	}
	
	
}
