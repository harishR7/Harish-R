package com.example.demo.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AccountHolder;
import com.example.demo.MobileRequest;
import com.example.demo.service.AccountHolderService;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping (path ="/api/v1/details")
@CrossOrigin(origins ="*")
public class AccountHolderController {

	@Autowired
	private AccountHolderService service;
	
	@Autowired
	private AdminService admin;
	
	
	
	@GetMapping
	public List<AccountHolder> findAll(){
		
		return this.service.findAll();
	}
 	
	@PostMapping(path="/add")
	public AccountHolder add( @RequestBody AccountHolder entity) {
		
		return this.service.add(entity);
	}
	
	@GetMapping(path = "/{accountNumber}")
    public AccountHolder findByAccountNumber(@PathVariable("accountNumber") long accountNumber) {
		
		return this.service.findByAccountNumber(accountNumber);
	}
	
	
	@GetMapping(path = "/balance/{accountNumber}")
	public double balanceAmount(@PathVariable("accountNumber") long accountNumber) {
		
		return this.service.balanceAmount(accountNumber);
	}
	
	
	@PostMapping(path = "/mobilereq")
	public MobileRequest mobileReq(@RequestBody MobileRequest entity) {
		return this.service.mobilerequest(entity);
	}
	
	@GetMapping(path = "/mobilereq/view")
	public List<MobileRequest> viewMobileReq() {
		return this.service.viewMobileRequests();
	}
	
	
	@PutMapping(path = "/transfer/{accountNumber1}/{accountNumber2}/{amount}")
	public Object transferAmount(@PathVariable("accountNumber1")long accountNumber1,@PathVariable("accountNumber2")long accountNumber2,@PathVariable("amount")double amount) {
	
		return this.service.balanceAfterTransfer(amount, accountNumber1, accountNumber2);
	}
	
	@PutMapping(path = "/admin/mobileupdate/{mobileNumber}/{accountNumber}")
	public int verifyMobileNo(@PathVariable("accountNumber")long accountNumber,@PathVariable("mobileNumber") long mobileNumber) {
		return this.service.verifyMobileNumber(mobileNumber , accountNumber );
}
	
	@PutMapping(path = "admin/addAmount/{accountNumber1}/{amount}")
	public Object addAmountToAccount(@PathVariable("accountNumber1")long accountNumber1,@PathVariable("amount")double amount) {
	
		return this.service.addAmount(amount, accountNumber1);
	}
	
	
	
}
