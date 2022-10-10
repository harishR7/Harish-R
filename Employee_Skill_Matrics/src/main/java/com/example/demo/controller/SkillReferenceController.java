package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SkillReference;
import com.example.demo.service.SkillReferenceService;

@RestController
@RequestMapping("api/v1")
public class SkillReferenceController {

	
	@Autowired
	private SkillReferenceService service;
	
	@PostMapping(path = "/skillRef")
	public Object addSkillDetails(@RequestBody SkillReference entity) {
		return this.service.addSkill(entity);
	}
	
	
	@GetMapping(path = "/skillRef")
	public List<SkillReference> findAll(){
		return this.service.findAll();
	}
	
	
}
