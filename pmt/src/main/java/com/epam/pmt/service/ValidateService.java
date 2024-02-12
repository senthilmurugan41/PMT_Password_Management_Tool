package com.epam.pmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;

@Service
public interface ValidateService {

	public boolean validateAccount(Account account) throws UserException;
	
	public boolean validateAccountList(List<Account> accounts,Account account) throws UserException;
	
	public boolean validatePassword(Account account) throws UserException;
	
	public boolean validateUrl(String url) throws UserException;
	
}
