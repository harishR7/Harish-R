package com.example.demo.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)


public class MobileRequest {

	long accountNumber;
	
	long oldMobileNumber;
	
	long newMobileNumber;
}
