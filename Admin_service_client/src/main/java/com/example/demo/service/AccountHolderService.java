package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.AccountHolder;
import com.example.demo.MobileRequest;
import com.example.demo.repo.AccountHolderRepository;
import com.example.demo.repo.MobileRequestRepo;

@Service
public class AccountHolderService {
	
	@Autowired
	private AccountHolderRepository repo;
	
	@Autowired
	private AccountHolder account;
	
	@Autowired
	private MobileRequestRepo mobileReq;
	
	public List<AccountHolder> findAll(){
		
		return this.repo.findAll();
	}
	
	public AccountHolder findByAccountNumber(long accountNumber) {
		
		return this.repo.findByAccountNumber(accountNumber);
	}
	
	public AccountHolder add(AccountHolder entity) {
		
		return this.repo.save(entity);
	}
	
	public double balanceAmount(long accountNumber) {
		
		AccountHolder account = findByAccountNumber(accountNumber);
		
		double balance = account.getBalance();
		
		return balance;
	}
	
	
	public MobileRequest mobilerequest(MobileRequest entity ) {
		
	if(this.repo.existsById(entity.getAccountNumber())) {
		
		
		this.mobileReq.save(entity);
	}
	
	
	return entity;	
	}
	
	
	public List<MobileRequest> viewMobileRequests(){
		return this.mobileReq.findAll();
	}
	
	

	
// transfer amount from one to another
public Object balanceAfterTransfer(double amount,
		long accountNumber1,long accountNumber2) {
	
	AccountHolder account1 = findByAccountNumber(accountNumber1);
	AccountHolder account2 = findByAccountNumber(accountNumber2);
	
	double amt = 0.0;
	double amt1 = 0.0;
	
	if (account1.getBalance() >= amount) {
		
		amt = account2.getBalance()+ amount;
	
		account2.setBalance(amt);
		repo.balanceAfterTransfer1(amt, accountNumber2);
		
		amt1 = account1.getBalance()-amount;
		account1.setBalance(amt1);
		
		//list.add(amt1);
		repo.balanceAfterTransfer(amt1, accountNumber1);
	
	}else
	{
		
		return  "Pothumaana alavuku kaasu illada ";
	}
	
	return account1.getBalance();
}


//verify and update the number and delete it from mobilereq table
public int verifyMobileNumber(long mobileNumber,long accountNumber) {
	
	int num= this.repo.verifyMobileNumber( mobileNumber,accountNumber);
	
	 int num2=this.mobileReq.deleteInRequest(accountNumber);
	
		return (num==num2)?1:0; 
		
}

// add amount to any account
	
public  Object addAmount(double amount,long accountNumber1) {
	AccountHolder account1 = findByAccountNumber(accountNumber1);
	
	double amt = 0.0;
	
	amt = account1.getBalance()+ amount;
	
	account1.setBalance(amt);
	this.repo.balanceAfterTransfer(amt, accountNumber1);
	
	return account1.getBalance();
}


}

	
	

