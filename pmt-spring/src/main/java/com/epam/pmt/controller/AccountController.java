package com.epam.pmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.AuthRequest;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.AccountService;
import com.epam.pmt.util.JwtUtil;

@RestController
@CrossOrigin
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/")
	public String login()
	{
		return "login successfull";
	}
	
	 @PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
	        try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	        }
	        return jwtUtil.generateToken(authRequest.getUserName());
	    }
		
	@PostMapping(path = "addAccount")
	public @ResponseBody AccountDto addAccount(@RequestBody AccountDto accountDto) throws UserException
	{
		return accountService.createAccount(accountDto);
	}
	@GetMapping(path="getAllAccount")
	public @ResponseBody List<AccountDto> getAllAccount()
	{
		return accountService.getAllAccount();
	}
	
	@PutMapping(path="updateAccount/{id}")
	public @ResponseBody AccountDto updateAccount(@RequestBody AccountDto accountDto,@PathVariable int id) throws UserException
	{
		return accountService.updateAccount(accountDto, id);
	}
	@DeleteMapping(path="/deleteAccount/{id}")
	public @ResponseBody AccountDto deleteAccount(@PathVariable int id) throws UserException
	{
		return accountService.deleteAccount(id);
	}
	
	@GetMapping(path="/getAccountCount")
	public long getAccountCount()
	{
		return accountService.getAccountCount();
	}

}
