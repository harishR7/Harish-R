package com.example.demo.entity;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "hr1_emp_skill")
public class EmployeeSkills {

	
	
		@Id
		@Column(name="emp_skill_id")
		int empSkillId;
		
		
		@Column(name="emp_id")
		int empId;
		
		@Column(name="skill_id")
		int skillId;
		
		
		@Column(name="skill_name")
		
		String skillName;
		
		@Column(name="skill_experience")
		String skillExperience;
		
		
		@Column(name="status")
		String status;
		
		@Column(name="approved_by")
		String approvedBy;
		
		@Column(name="approved_date")
		@DateTimeFormat(iso = ISO.DATE)
		Date approvedDate;
		
		
		@Column(name="created_date")
		@DateTimeFormat(iso = ISO.DATE)
		Date createdDate;
		

		@Column(name="modified_date")
		@DateTimeFormat(iso = ISO.DATE)
		Date modifiedDate;

	}


