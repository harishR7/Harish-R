package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmployeeSkills;
import com.example.demo.repo.EmployeeSkillClass;
import com.example.demo.service.EmployeeSkillService;

@RestController
@RequestMapping("api/v1")
public class EmployeeSkillController {

	@Autowired
	private EmployeeSkillService service;
	
	@Autowired
	private EmployeeSkillClass classService;
	
	@PostMapping("/skillEmp")
	public Object addEmpSkills(@RequestBody EmployeeSkills entity) {
		return this.service.addEmpSkill(entity)	;
		}
	
	@PutMapping("/skillEmp/update")
	public Object updateEmpSkills(@RequestBody EmployeeSkills entity) {
		return this.service.updateEmpSkill(entity);
		}
	
	@GetMapping("/skillEmp/{name}")
	public Object findByName(@PathVariable("name") String name ) {
		return this.classService.findByEmpName(name);
	}
	
	@GetMapping("/skillEmp/NameOrExp/{value}")
	public List<EmployeeSkills> findByNameOrExp(@PathVariable("value") String name,@PathVariable("value")String experience){
		return this.service.findBySkillNameorExp(name, experience);
	}
	@GetMapping("/skillEmp/NameOrstatusOrApproved/{value}")
	public Object searchByValue(@PathVariable("value") String name,@PathVariable("value")String approvedBy,@PathVariable("value")String status){
	return  this.service.searchByValue(name, approvedBy, status);
	}
	
	
	@PutMapping("/skillEmp/managerApproved/{name}/{status}")
	public Object updateStatusAsApproved(@PathVariable ("name") String name,@PathVariable("status")String status) {
		return this.classService.updateStatusAsApproved(name, status);
	}
}
