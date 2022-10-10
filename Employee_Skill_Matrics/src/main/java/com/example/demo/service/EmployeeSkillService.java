package com.example.demo.service;




import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.EmployeeSkills;
import com.example.demo.entity.SkillReference;
import com.example.demo.entity.TransactionLog;
import com.example.demo.repo.EmployeeDetailsRepository;
import com.example.demo.repo.EmployeeSkillClass;
import com.example.demo.repo.EmployeeSkillRepo;
import com.example.demo.repo.EmployeeSkillRepoWithJpa;
import com.example.demo.repo.SkillReferenceRepo;
import com.example.demo.repo.TransactionLogRepo;

@Service
public class EmployeeSkillService {

	@Autowired
	private EmployeeSkillClass skillrepo;
	@Autowired
	private EmployeeDetailsRepository empRepo;
	
	@Autowired
	private SkillReferenceRepo skillRef;
	
	@Autowired
	private TransactionLogRepo transRepo;
	
	@Autowired
	private EmployeeSkillRepoWithJpa skillWithJpa;
	
	
	public Object addEmpSkill(EmployeeSkills entity) {
		
		TransactionLog transaction=new TransactionLog();
		transaction.setApiOperationName("EmployeeSkillDetailsAreBeenAdded");
		transaction.setCreatedDate(Date.valueOf(LocalDate.now()));
		transaction.setRequest(entity.toString());
		transaction.setCreatedTime(new SimpleDateFormat("HH.mm aa").format(new java.util.Date()));
		
		
		
		
		
		entity.setStatus("DRAFT");
		entity.setCreatedDate(Date.valueOf(LocalDate.now()));
		entity.setModifiedDate(Date.valueOf(LocalDate.now()));
		
		if(empRepo.existsById(entity.getEmpId())) {
			Optional<SkillReference> result=skillRef.findBySkillName(entity.getSkillName());
	
			if(result.isPresent())
			{
				int id=skillRef.findBySkillName(entity.getSkillName()).get().getSkillId();
				entity.setSkillId(id);
				transaction.setResponse(entity.toString());
				transRepo.save(transaction);
		        return this.skillrepo.empSkillAdd(entity);
			}
			else {
				transaction.setResponse("Invalid Skill Name");
				transRepo.save(transaction);
				return "Invalid Skill Name";
			}
		}
		else {
			transaction.setResponse("Empolyee Id Not valid");
			transRepo.save(transaction);
			return "Empolyee Id Not valid";
		}
		
	}
	
	
	
	public Object  updateEmpSkill(EmployeeSkills entity) {
		

		TransactionLog transaction=new TransactionLog();
		transaction.setApiOperationName("EmployeeSkillDetailsAreBeenUpdated");
		transaction.setCreatedDate(Date.valueOf(LocalDate.now()));
		transaction.setRequest(entity.toString());
		transaction.setCreatedTime(new SimpleDateFormat("HH.mm aa").format(new java.util.Date()));
		
		
		
		entity.setModifiedDate(Date.valueOf(LocalDate.now()));
		Optional<SkillReference> result=skillRef.findBySkillName(entity.getSkillName());	
		if(result.isPresent())
		{
			int id=skillRef.findBySkillName(entity.getSkillName()).get().getSkillId();
			entity.setSkillId(id);
			transaction.setResponse(entity.toString());
			transRepo.save(transaction);
	        return this.skillrepo.empSkillUpdate(entity);
		}
		else {
			transaction.setResponse("Invalid Skill Name");
			transRepo.save(transaction);
			return "Invalid Skill Name";
		}
	}
		
	public List<EmployeeSkills> findBySkillNameorExp(String name, String experience){
		
		return this.skillWithJpa.findBySkillNameorExp(name, experience);
	}
	//API-8
	public Object searchByValue(String name ,String approvedBy,String status){
		
		
		
		
		List<EmployeeDetails> list=this.empRepo.findByEmployeeName(name);
		
		if(list.isEmpty()) {
			
			
			if(skillWithJpa.findByApprovedByOrStatus(approvedBy, status)) {
				
				return this.skillWithJpa.searchByValue(0, approvedBy, status);
			}
			else {
				
				return "Invalid input";
			}
		}
		else {
			int id=this.empRepo.findByEmployeeName(name).get(0).getEmployeeId();
		
			return this.skillWithJpa.searchByValue(id, approvedBy, status);
		}
		
		
	}
	//API-7
	public Object updateStatusAsApproved(String name,String status) {
		TransactionLog transaction=new TransactionLog();
		transaction.setApiOperationName("updateStatusAsApproved");
		transaction.setCreatedDate(Date.valueOf(LocalDate.now()));
		transaction.setRequest("Name="+name+","+"Status="+status);
		transaction.setCreatedTime(new SimpleDateFormat("HH.mm aa").format(new java.util.Date()));
		transaction.setResponse(this.skillrepo.updateStatusAsApproved(name, status).toString());
		transRepo.save(transaction);
		return this.skillrepo.updateStatusAsApproved(name, status);
	}
	}
		
	

