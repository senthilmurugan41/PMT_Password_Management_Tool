package com.epam.pmt.service.impl;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.repository.AccountRepository;

@SpringBootTest
class AccountServiceImplTest {
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@MockBean
	private AccountRepository accountRepository;
	
	@Test
	void updateAccountTest()
	{
		AccountDto accountDto=new AccountDto();
		when(accountRepository.findById(10)).thenReturn(Optional.empty());
		assertThrows(UserException.class,()->accountServiceImpl.updateAccount(accountDto, 10));
	}
	
	@Test
	void deleteAccountTest()
	{
		when(accountRepository.findById(10)).thenReturn(Optional.empty());
		assertThrows(UserException.class,()->accountServiceImpl.deleteAccount(10));
	}

}
