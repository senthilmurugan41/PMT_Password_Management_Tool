package com.epam.pmt.service.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;

@SpringBootTest
class ValidationServiceImplTest {

	
	@Autowired
	private ValidateServiceImpl validateServiceImpl;

	@Test
	void validateAccountTest()
	{
		Account account=new Account();
		account.setAccountName("java");
		assertTrue(validateServiceImpl.validateAccount(account));
	}
	
	@Test
	void invalidAccountTest()
	{
		Account account=new Account();
		account.setAccountName("1234");
		assertThrows(UserException.class,()-> validateServiceImpl.validateAccount(account));
	}
	
	@Test
	void validatePasswordTest()
	{
		Account account=new Account();
		account.setPassword("java");
		assertTrue(validateServiceImpl.validatePassword(account));
	}
	@Test
	void invalidPasswordTest()
	{
		Account account=new Account();
		account.setPassword("javafkafdhakhsfdafaslkjlsdaj"
				+ "jlkjalkjflakjdlfakjfsa"
				+ "ajflajflasjflasjlf"
				+ "flajklfajklfjaldfja"
				+ "flajflkasjlkfajlkfda"
				+ "jalkjflkajslfjsal"
				+ "lfkajlkfadjlksdajlkfasjldsfjaljflsadjf");
		assertThrows(UserException.class,()-> validateServiceImpl.validatePassword(account));
	}
	
	@Test
	void validateUrl()
	{
		assertThrows(UserException.class,()->validateServiceImpl.validateUrl("java"));
	}
	
	@Test
	void invalidAccountList()
	{
		List<Account>accounts=new ArrayList<>();
		
		Account account=new Account();
		account.setAccountName("java");
		accounts.add(account);
		
		assertThrows(UserException.class, ()-> validateServiceImpl.validateAccountList(accounts, account));
		
	}
	
	@Test
	void invalidGroupList()
	{
		List<Group> groups=new ArrayList<>();
	    Group group =new Group();
	    
	    group.setGroupName("java");
	    groups.add(group);
		
		assertThrows(UserException.class, ()-> validateServiceImpl.validateGroupList(groups, group));
		
	}
	
}
