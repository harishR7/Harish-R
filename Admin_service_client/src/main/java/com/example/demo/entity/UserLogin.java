package com.example.demo.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "bank_user_login")
public class UserLogin {

	
	@Id
	@Column(name = "account_number")
	long accountNumber;
	
	@Column(name = "user_name")
	String userName;
	
	
	@Column(name = "password")
	String password;
}
