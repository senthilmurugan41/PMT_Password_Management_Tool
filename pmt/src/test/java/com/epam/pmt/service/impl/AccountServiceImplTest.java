package com.epam.pmt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.epam.pmt.dao.impl.AccountJPAImpl;
import com.epam.pmt.dao.impl.GroupJPAImpl;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.AccountService;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AccountServiceImplTest {
	AccountService accountService;
	
	@Mock
	private GroupJPAImpl groupDao;
	
	@Mock
	private AccountJPAImpl accountDao;
	
	@Mock
	private ValidateServiceImpl validateService;
	
	@Mock
	private PasswordServiceImpl passwordService;
	
	@BeforeEach
	void setUp()
	{
		 accountService=new AccountServiceImpl();
		 accountService=new AccountServiceImpl(accountDao,groupDao,
				 validateService,passwordService);
		 
	}
	
	@Test
	@Order(1)
	void validAccount() throws UserException
	{
		Account account=new Account();
		account.setAccountName("mango");
		account.setPassword("senthil");
		account.setAccountGroup("social");
		account.setUrl("https://mango.com");
		Group group=new Group();
		List<Account> accounts=new ArrayList<Account>();
		group.setGroupName(account.getAccountGroup());
		when(accountDao.getAccount()).thenReturn(accounts);
		when(groupDao.addGroup(group)).thenReturn(group);
		when(validateService.validateUrl(anyString())).thenReturn(true);
		when(validateService.validateAccountList(accounts, account)).thenReturn(true);
		assertTrue(accountService.createPassword(account));
		
	}
	
	@Test
	@Order(2)
	void groupAlreadyPresent() throws UserException
	{
		Account account=new Account();
		account.setAccountName("mango");
		account.setPassword("senthil");
		account.setAccountGroup("social");
		account.setUrl("https://mango.com");
		Group group=new Group();
		List<Account> accounts=new ArrayList<Account>();
		group.setGroupName(account.getAccountGroup());
		when(accountDao.getAccount()).thenReturn(accounts);
		when(groupDao.addGroup(group)).thenReturn(group);
		when(validateService.validateUrl(anyString())).thenReturn(true);
		when(validateService.validateAccountList(accounts, account)).thenReturn(true);
		when(groupDao.readGroup(account.getAccountGroup())).thenReturn(group);
		assertTrue(accountService.createPassword(account));
	}
	
	@Test
	@Order(3)
	void UnassignedGroup() throws UserException
	{
		Account account=new Account();
		account.setAccountName("facebook");
		account.setPassword("murugan");
		account.setAccountGroup("");
		account.setUrl("https://facebook.com");
		Group group=new Group();
		List<Account> accounts=new ArrayList<Account>();
		group.setGroupName(account.getAccountGroup());
		when(accountDao.getAccount()).thenReturn(accounts);
		when(groupDao.addGroup(group)).thenReturn(group);
		when(validateService.validateUrl(anyString())).thenReturn(true);
		when(validateService.validateAccountList(accounts, account)).thenReturn(true);
		assertTrue(accountService.createPassword(account));
	}
	
	@Test
	@Order(4)
	void differentGroup() throws UserException
	{
		Account account=new Account();
		account.setAccountName("whatsapp");
		account.setPassword("senthil");
		account.setAccountGroup("app");
		account.setUrl("https://whatsapp.com");
		Group group=new Group();
		List<Account> accounts=new ArrayList<Account>();
		group.setGroupName(account.getAccountGroup());
		when(accountDao.getAccount()).thenReturn(accounts);
		when(groupDao.addGroup(group)).thenReturn(group);
		when(validateService.validateUrl(anyString())).thenReturn(true);
		when(validateService.validateAccountList(accounts, account)).thenReturn(true);
		assertTrue(accountService.createPassword(account));
	}
	
	@Test
	@Order(5)
	void validReadAccount() throws UserException
	{
		String expected="https://whatsapp.com";
		Account account =new Account();
		account.setUrl(expected);
		when(accountDao.findAccountByName("twitter")).thenReturn(account);
		
		assertEquals(expected, accountService.readPasswordByAccount("twitter").getUrl());
	}
	
	@Test
	@Order(6)
	void displayAccountByGroup() throws UserException
	{
		Account account=new Account();
		account.setAccountName("twitter");
		account.setPassword("murugan");
		account.setAccountGroup("social");
		account.setUrl("https://twitter.com");
		Group group=new Group();
		List<Account> accountsExpected=new ArrayList<Account>();
		accountsExpected.add(account);
		group.setGroupName(account.getAccountGroup());
		when(accountDao.findAccountByGroupName(anyString())).thenReturn(accountsExpected);
		List<Account> accounts=accountService.displayAllAccount("social");
		assertEquals("twitter", accounts.get(0).getAccountName());
		
	}
	
	@Test
	@Order(7)
	void removeAccount() throws UserException
	{
		when(accountDao.removeAccount(anyString())).thenReturn(true);
		assertTrue(accountService.removeAccount("twitter"));
	}
	
	@Test
	@Order(8)
	void updateAccount() throws UserException
	{
		
		when(accountDao.updateAccount(anyString(), anyString())).thenReturn(new Account());
		assertTrue(accountService.updatePassword("facebook", "senthil"));
	}
	
	@Test
	@Order(9)
	void ReadAccountExceptionCheck() throws UserException
	{
		when(accountDao.findAccountByName("java")).thenThrow(UserException.class);
		assertThrows(UserException.class, ()->accountService.readPasswordByAccount("java"));
		
	}
	
//	@Test
//	void UpdateAccountExcecption()
//	{
//		Exception exception= assertThrows(UserException.class, ()->accountService.updatePassword("java", "java"));	
//		
//		String expected="Account Not Found!!!";
//		
//		assertEquals(expected,exception.getMessage());
//	}
//	
//	@Test
//	void removeAccountException()
//	{
//		Exception exception= assertThrows(UserException.class, ()->accountService.removeAccount("java"));
//		String expected="Account Not Found!!!";
//		assertEquals(expected,exception.getMessage());
//
//	}
//	
//	@Test 
//	void removeAccountByGroupException()
//	{
//		Exception exception= assertThrows(UserException.class, ()->AccountDAOImpl.getInstance().removeAccountByGroup("java"));
//		String expected="Group Not Found!!!";
//		assertEquals(expected,exception.getMessage());
//	}
//	
	
	 
	
	
}
