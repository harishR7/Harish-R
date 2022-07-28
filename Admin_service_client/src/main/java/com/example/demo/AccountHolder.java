package com.example.demo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
@Entity
@Table(name="bank_client_details")

@Component
public class AccountHolder {

	@Id
	@Column(name = "account_number")
	long accountNumber;
	
	@Column(name = "account_holder_name")
	String accountHolderName;
	
	@Column(name = "address")
	String address;
	
	@Column(name = "mobile_number")
	long mobileNumber;
	
	@Column(name = "date_of_birth")
	@DateTimeFormat(iso=ISO.DATE)
	LocalDate dateOfBirth;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "balance")
	double balance;
	
	
	
	
	
}
