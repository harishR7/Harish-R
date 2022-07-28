package com.example.demo.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.AccountHolder;



@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

	AccountHolder findByAccountNumber(long accountNumber);
	
	
	@Query(nativeQuery=true,value = "update bank_client_details set balance=:amount where account_number=:accountNumber")
	@Transactional
	@Modifying
	Object  balanceAfterTransfer(@Param("amount") double amount,@Param("accountNumber") long accountNumber);

	
	@Query(nativeQuery=true,value = "update bank_client_details set balance=:amount where account_number=:accountNumber")
	@Transactional
	@Modifying
	Object balanceAfterTransfer1(@Param("amount") double amount,@Param("accountNumber") long accountNumber);
	
	
	
	@Query(nativeQuery = true,value = "update bank_client_details set mobile_number=:mobileNumber where account_number=:accountNumber")
	@Transactional
	@Modifying
	int verifyMobileNumber(@Param("mobileNumber") long mobileNumber,@Param("accountNumber")long accountNumber);
	
	
	
	
}
