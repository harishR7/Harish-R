package com.example.demo.entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.IsoEra;

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
@Table(name = "hr1_transation_log")
public class TransactionLog {
	
		
		@Id
		@Column(name="transaction_id")
		int transactionId;
		
		
		@Column(name="user_login_id")
		int userLoginId;
		
		@Column(name="api_operation_name")
		int apiOperationName;
		
		
		@Column(name="request")
	    String request;
		
		@Column(name="response")
		String response;
		
		
		@Column(name="created_time")
		@DateTimeFormat(iso = ISO.TIME)
		Time createdTime;

		@Column(name="created_date")
		@DateTimeFormat(iso = ISO.DATE)
		Date createdDate;
		

	}


