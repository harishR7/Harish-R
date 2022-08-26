package com.example.demo.repo;

import java.util.List;

import com.example.demo.entity.EmployeeSkills;

public interface EmployeeSkillRepo {

	public int empSkillUpdate(EmployeeSkills entity);
	public int empSkillAdd(EmployeeSkills entity);
	
	public Object updateStatusAsApproved(String name,String status);
	
	public Object findByEmpName(String name);
	
}
