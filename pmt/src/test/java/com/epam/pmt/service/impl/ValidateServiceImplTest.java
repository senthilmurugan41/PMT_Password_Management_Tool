package com.epam.pmt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.ValidateService;

@TestMethodOrder(OrderAnnotation.class)
class ValidateServiceImplTest {

	ValidateService validateService;
	@BeforeEach
	void setUp()
	{
		validateService=new ValidateServiceImpl();
		
	}
	
	@Test
	@Order(1)
	void validatePassoword() throws UserException
	{
		Account account=new Account("test","https://test.com","testing","test");
		assertTrue(validateService.validatePassword(account));
	}
	
	@Test
	@Order(2)
	void validateAccountException()
	{
		Account account=new Account("@^%#%*&*","https://twitter.com","senthil","social");
		UserException exception=assertThrows(UserException.class, ()->validateService.validateAccount(account));
	    assertEquals("Not a valid Account",exception.getMessage());
	}
	@Test
	@Order(3)
	void validateEmptyAccountNameException()
	{
		Account account=new Account("","https://twitter.com","senthil","social");
		UserException exception=assertThrows(UserException.class, ()->validateService.validateAccount(account));
	    assertEquals("Not a valid Account",exception.getMessage());
	}
	@Test
	@Order(4)
	void validateNullAccountName()
	{
		Account account=new Account(null,"https://twitter.com","senthil","social");
		UserException exception=assertThrows(UserException.class, ()->validateService.validateAccount(account));
	    assertEquals("Not a valid Account",exception.getMessage());
	}
	
	@Test
	@Order(5)
	void validatePasswordException()
	{
		Account account=new Account("twitter","https://twitter.com","senthilfafafasfjlakjlfsajlfasjlsjdfljasljlsfjlasjlkasjflkasjlfsajlfsjalkfdsjlfsajljfsdljsalkflkasjlfdslkfsjaljfljfas;jfsaljfdsljfdsljfsdakljflkajfdlaj","social");
		UserException exception=assertThrows(UserException.class,()->validateService.validatePassword(account));
		assertEquals("Not a valid Password",exception.getMessage());
	}
	
	@Test
	@Order(6)
	void validateAccountList() throws UserException
	{
		List<Account> accounts=Arrays.asList(
				new Account("facebook","https://facebook.com","senthil","social")
				);
		Account account=new Account("instagram","https://instagram.com","admin","media");
		assertTrue(validateService.validateAccountList(accounts,account));
	}
	@Test
	@Order(7)
	void validateAccountListException()
	{
		Account account=new Account("facebook","https://facebook.com","senthil","social");
		List<Account> accounts=Arrays.asList(account);
		
		UserException exception=assertThrows(UserException.class,()->validateService.validateAccountList(accounts,account));
		assertEquals("Account Already Present", exception.getMessage());
	}
	@Test
	@Order(8)
	void validateUrlException()
	{
		UserException exception=assertThrows(UserException.class,()->validateService.validateUrl("java"));
		
		assertEquals("Not a valid Url", exception.getMessage());
	}
}
