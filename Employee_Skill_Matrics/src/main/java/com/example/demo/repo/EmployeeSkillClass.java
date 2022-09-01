package com.example.demo.repo;


import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AssociateToManager;
import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.entity.EmployeeSkills;
import com.example.demo.entity.SkillReference;

@Repository
public class EmployeeSkillClass implements EmployeeSkillRepo {

	  
	@Autowired
	private JdbcTemplate template;
	@Autowired
	private EmployeeDetailsRepository empRepo;
	@Autowired
	private AssociateToManagerRepo associateRepo;
	@Autowired
	private EmployeeSkillRepoWithJpa skillWithJpa;
	@Override
	public int empSkillUpdate (EmployeeSkills entity) {

			String update="update hr1_emp_skill set skill_id=?,skill_name=?,skill_experience=?,modified_date=? where emp_id=?";
			return template.update(update,entity.getSkillId(),entity.getSkillName(),entity.getSkillExperience(),entity.getModifiedDate(),entity.getEmpId());	
	}

	@Override
	public int empSkillAdd(EmployeeSkills entity) {
		// TODO Auto-generated method stub
		String sql="insert into hr1_emp_skill(emp_id,skill_name,skill_experience,emp_skill_id,status,created_date,modified_date,skill_id) values(?,?,?,?,?,?,?,?)";
		return  template.update(sql,entity.getEmpId(),entity.getSkillName(),entity.getSkillExperience(),entity.getEmpSkillId(),entity.getStatus(),entity.getCreatedDate(),entity.getModifiedDate(),entity.getSkillId());
	}

	
	@Override
	public Object findByEmpName(String name) {
		
		List<EmployeeSkills> statusList=new ArrayList<>();
		
		//List<EmployeeSkills> skillList=null;
		List<EmployeeDetails> empList=empRepo.findByEmployeeName(name);
		List<EmployeeDetails> managerList=empList.stream().filter((e)-> e.getEmployeeDesignationId()==101).collect(toList());
		int managerId=managerList.get(0).getEmployeeId();
		
		List<AssociateToManager> empAssociatedList=this.associateRepo.findByManagerId(managerId);
		for(AssociateToManager eachValue: empAssociatedList) {
			int value=eachValue.getEmployeeId();
			

			String basedOnStatus="select * from hr1_emp_skill where status =? and emp_id=?";
			   Optional<List<EmployeeSkills>>result =Optional.of(template.query(basedOnStatus,BeanPropertyRowMapper.newInstance(EmployeeSkills.class),"DRAFT",value));
			   if(result.isPresent()) {
				   statusList.addAll(result.get());
				   
			   }

		}
		if(statusList.isEmpty()) {
			return "No draft";
			
		}
		else {
			return statusList;
		}
		
		
	
	
		
		//return empAssociatedList;
		
		
		//String nameList="select * from hr1_emp_desigination";
		//  return template.query(nameList,BeanPropertyRowMapper.newInstance(EmployeeDesignation.class));
		 
		
			//	return  list.stream().filter((e)-> e.getEmployeeDesignationId()==101).collect(toList());
						//.mapToInt((e)->e.getEmployeeId());
		// int managerId=Integer.parseInt(manId);
		 
	// String managerAssociate="select e.* from hr1_associateto_m m ,hr1_emp_skill e  where m.manager_id=? and e.status=?";
		// return template.query(managerAssociate,BeanPropertyRowMapper.newInstance(AssociateToManager.class),BeanPropertyRowMapper.newInstance(EmployeeSkills.class),managerId ,"DRAFT");
		 
		// String basedOnStatus="select * from hr1_emp_skill where status =?";
		   
		//List<EmployeeSkills> statusList=( template.query(basedOnStatus,BeanPropertyRowMapper.newInstance(EmployeeSkills.class),"DRAFT"));
		 
//		 for(AssociateToManager eachValue : empList) {
//			int empId= eachValue.getEmployeeId();
//			String skillBased="select * from hr1_emp_skill where emp_id =?";
//			skillList.add(template.query(skillBased,BeanPropertyRowMapper.newInstance(EmployeeSkills.class),empId ));
//			for(EmployeeSkills value:skillList) {
//				String status=value.getStatus();
//				String basedOnStatus="select * from hr1_emp_skill where status =?";
//				   
//				statusList.addAll( template.query(basedOnStatus,BeanPropertyRowMapper.newInstance(EmployeeSkills.class),status ));
//			
//			
//			}
//		 }
		// return  statusList;
		
		
		
	}

	

	@Override
	public Object updateStatusAsApproved(String name,String status) {
		// TODO Auto-generated method stub
		List<Integer> skillList=new ArrayList<>();
		int count=0;
		List<EmployeeDetails> empList=empRepo.findByEmployeeName(name);
		List<EmployeeDetails> managerList=empList.stream().filter((e)-> e.getEmployeeDesignationId()==101).collect(toList());
		int managerId=managerList.get(0).getEmployeeId();
		
		List<AssociateToManager> empAssociatedList=this.associateRepo.findByManagerId(managerId);
		int empNo=this.associateRepo.findByManagerId(managerId).size();
		for(AssociateToManager eachValue: empAssociatedList) {
			int value=eachValue.getEmployeeId();
			System.out.println(value);
			Optional<EmployeeSkills> answer=skillWithJpa.findByEmpIdAndStatus(value, status);
			if(answer.isPresent()) {
				count++;
				System.out.println(count);
				if(count==empNo) {
				return "already updated";
				}
			}
			else {
			String updateStatus="update hr1_emp_skill set status=?,approved_by=?,approved_date=? where emp_id=?";
			Optional<Integer>result =Optional.of(template.update(updateStatus,status,name,Date.valueOf(LocalDate.now()),value));
			   if(result.isPresent()) {
				   skillList.add(result.get());
				   
			   }
		}
		}
		
		if(skillList.isEmpty()) {
			return "No draft";
			
		}
		else {
			return "Updated";
		}
	}

}
