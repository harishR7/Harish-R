package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Table(name ="bank_admin_details")
@Entity
@Component
public class Admin {

	
	@Id
	@Column(name = "admin_id")
	int adminId;
	
	@Column(name = "admin_name")
	String adminName;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "mobil_number")
	long mobileNumber;
	
	
	
	
	
	
}
