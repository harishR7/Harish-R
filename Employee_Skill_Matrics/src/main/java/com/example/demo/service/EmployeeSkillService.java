package com.example.demo.service;




import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.EmployeeSkills;
import com.example.demo.entity.SkillReference;
import com.example.demo.repo.EmployeeDetailsRepository;
import com.example.demo.repo.EmployeeSkillClass;
import com.example.demo.repo.EmployeeSkillRepo;
import com.example.demo.repo.EmployeeSkillRepoWithJpa;
import com.example.demo.repo.SkillReferenceRepo;

@Service
public class EmployeeSkillService {

	@Autowired
	private EmployeeSkillClass skillrepo;
	@Autowired
	private EmployeeDetailsRepository empRepo;
	
	@Autowired
	private SkillReferenceRepo skillRef;
	
	@Autowired
	private EmployeeSkillRepoWithJpa skillWithJpa;
	
	
	public Object addEmpSkill(EmployeeSkills entity) {
		entity.setStatus("DRAFT");
		entity.setCreatedDate(Date.valueOf(LocalDate.now()));
		entity.setModifiedDate(Date.valueOf(LocalDate.now()));
		
		if(empRepo.existsById(entity.getEmpId())) {
			Optional<SkillReference> result=skillRef.findBySkillName(entity.getSkillName());
			
			
			
			if(result.isPresent())
			{
				int id=skillRef.findBySkillName(entity.getSkillName()).get().getSkillId();
				entity.setSkillId(id);
		        return this.skillrepo.empSkillAdd(entity);
			}
			else {
				return "Invalid Skill Name";
			}
		}
		else {
			return "Empolyee Id Not valid";
		}
		
	}
	
	
	
	public Object  updateEmpSkill(EmployeeSkills entity) {
		entity.setModifiedDate(Date.valueOf(LocalDate.now()));
		Optional<SkillReference> result=skillRef.findBySkillName(entity.getSkillName());	
		if(result.isPresent())
		{
			int id=skillRef.findBySkillName(entity.getSkillName()).get().getSkillId();
			entity.setSkillId(id);
	        return this.skillrepo.empSkillUpdate(entity);
		}
		else {
			return "Invalid Skill Name";
		}
	}
		
	public List<EmployeeSkills> findBySkillNameorExp(String name, String experience){
		return this.skillWithJpa.findBySkillNameorExp(name, experience);
	}
	
	public Object searchByValue(String name ,String approvedBy,String status){
		
		List<EmployeeDetails> list=this.empRepo.findByEmployeeName(name);
		if(list.isEmpty()) {
			
		return this.skillWithJpa.searchByValue(0, approvedBy, status);
		}
		else {
			int id=this.empRepo.findByEmployeeName(name).get(0).getEmployeeId();
			
			return this.skillWithJpa.searchByValue(id, approvedBy, status);
		}
		
		
	}
	public Object updateStatusAsApproved(String name,String status) {
		return this.skillrepo.updateStatusAsApproved(name, status);
	}
	}
		
	

