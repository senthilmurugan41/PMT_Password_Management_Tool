package com.epam.pmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;

@Repository
public interface AccountDAO  {
	
	public Account findAccountByName(String accountName) throws UserException;
	
	public List<Account> findAccountByGroupName(String groupName) throws UserException;

	public Account addAccount(Account account);
	
	public boolean removeAccount(String accountName) throws UserException;
	
	public boolean removeAccountByGroup(String groupName) throws UserException;
	
	public List<Account> getAccount();
	
	public Account updateAccount(String accountName,String password) throws UserException;
}
