package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "hr1_emp_details")
public class EmployeeDetails {

	
	@Id
	@Column(name = "emp_id")
	int employeeId;
	
	@Column(name = "emp_name")
	String employeeName;
	
	
	
	@Column(name = "emp_doj")
	@DateTimeFormat(iso = ISO.DATE)
	LocalDate employeeDoj;
	
	
   @Column(name = "emp_designation_id")
   int employeeDesignationId;

}
