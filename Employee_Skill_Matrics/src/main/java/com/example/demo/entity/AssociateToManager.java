package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "hr1_associateto_m")
public class AssociateToManager {

	
	@Id
	@Column(name = "emp_id")
	int employeeId;
	
	@Column(name = "emp_name")
	String employeeName;
	
	@Column(name = "manager_id")
	int managerId;
	}

