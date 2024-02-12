package com.epam.pmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.exception.UserException;

@Service
public interface AccountService {
	
  public AccountDto createAccount(AccountDto accountDto) throws UserException;
 
  public AccountDto updateAccount(AccountDto accountDto,int accountId) throws UserException;
  
  public AccountDto deleteAccount(int accountId) throws UserException;
  
  public long getAccountCount();
  
  public List<AccountDto> getAllAccount();
  

}
