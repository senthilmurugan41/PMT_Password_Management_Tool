package com.epam.pmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;

@Service
public interface AccountService {
	
  public boolean createPassword(Account account) throws UserException;
  
  public Account readPasswordByAccount(String accountName) throws UserException;
  
  public List<Account> displayAllAccount(String groupName) throws UserException;
  
  public boolean removeAccount(String accountName) throws UserException;
  
  public boolean updatePassword(String accountName,String password) throws UserException;
  

}
