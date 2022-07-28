package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.AccountHolder;
import com.example.demo.entity.MobileRequest;

@RestController
@RequestMapping(path = "/api/v1/")
@CrossOrigin(origins ="*")
public class ClientController {
	
	@Autowired
	private RestTemplate template;
	
	
	@GetMapping(path = "accountholders/balance/{id}")
	public String getBalance(@PathVariable("id") long id) {
		return this.template.getForObject("http://ADMIN-SERVICE-CLIENT/api/v1/details/balance/"+id,String.class);
	}
	
	@PostMapping(path = "accountholders/create")
	public AccountHolder[] register(@RequestBody AccountHolder account) {
		return this.template.postForObject("http://ADMIN-SERVICE-CLIENT/api/v1/details/add", account, AccountHolder[].class);}
	
	@PostMapping(path = "accountholders/mobilerequest")
	public MobileRequest[] mobileReq(@RequestBody MobileRequest mobilreq) {
		return this.template.postForObject("http://ADMIN-SERVICE-CLIENT/api/v1/details/mobilereq",mobilreq ,MobileRequest[].class);
	}
	
//	@PutMapping(path = "accountholders/moneyTransfer/{accountNumber1}/{accountNumber2}/{amount}")
//	public String moneyTransfer(@PathVariable("accountNumber1")long accountNumber1,@PathVariable("accountNumber2")long accountNumber2,@PathVariable("amount")double amount) {
//		return this.template.put("http://ADMIN-SERVICE-CLIENT/api/v1/details/mobilereq,accountNumber2,amount ,accountNumber1, );
//	}
//	
	
	
	
}
