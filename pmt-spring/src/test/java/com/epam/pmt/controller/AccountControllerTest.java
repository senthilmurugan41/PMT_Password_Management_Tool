package com.epam.pmt.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.repository.AccountRepository;
import com.epam.pmt.repository.GroupRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser
class AccountControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountController accountController;
	
	@MockBean
	private GroupRepository groupRepository;
	
	
	@Test
	void addAccount() throws Exception
	{
		AccountDto accountDto=new AccountDto();
		accountDto.setAccountName("twitter");
		accountDto.setGroupName("social");
		accountDto.setPassword("twitter");
		accountDto.setUrl("https://twitter.com");
		Group group=new Group();
		group.setGroupName("Java");
		Account account=new Account();
		account.setId(10);
		account.setAccountName("twitter");
		account.setPassword("YmFuYW5h");
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(groupRepository.findByGroupName("social")).thenReturn(Optional.of(group));
		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
		assertEquals("twitter", accountController.addAccount(accountDto).getAccountName());
		
	}
	
	@Test
	void getAllAccount() throws UnsupportedEncodingException, Exception
	{
		List<Account>accounts=new ArrayList<>();
		Account account=new Account();
		account.setId(10);
		account.setAccountName("twitter");
		account.setPassword("YmFuYW5h");
		accounts.add(account);
		when(accountRepository.findAll()).thenReturn(accounts);
		String result=mockMvc.perform(get("/getAllAccount").
				accept(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
		ObjectMapper objectMapper=new ObjectMapper();
		List<AccountDto> accountDtos=new ArrayList<AccountDto>();
		accountDtos=objectMapper.readValue(result, new TypeReference<List<AccountDto>>(){});
		assertEquals(1,accountDtos.size());
		
	
	}
	
	@Test
	void updateAccount() throws UserException
	{
		AccountDto accountDto=new AccountDto();
		accountDto.setAccountName("twitter");
		accountDto.setGroupName("social");
		accountDto.setPassword("twitter");
		accountDto.setUrl("https://twitter.com");
		Group group=new Group();
		group.setGroupName("Java");
		Account account=new Account();
		account.setId(10);
		account.setAccountName("twitter");
		account.setPassword("YmFuYW5h");
		
		when(accountRepository.findById(10)).thenReturn(Optional.of(account));
		when(accountRepository.save(account)).thenReturn(account);
		assertEquals("twitter",accountController.updateAccount(accountDto, 10).getAccountName());
	}
	
	@Test
	void deleteAccount() throws UserException
	{
		Account account=new Account();
		account.setId(10);
		account.setAccountName("twitter");
		account.setPassword("YmFuYW5h");
		when(accountRepository.findById(10)).thenReturn(Optional.of(account));
		assertEquals("twitter", accountController.deleteAccount(10).getAccountName());
	}
	
	@Test
	void getAccountCount()
	{
		when(accountRepository.count()).thenReturn(3l);
		assertEquals(3l, accountController.getAccountCount());
	}

	@Test
	void unAssignedAccount() throws UserException
	{
		AccountDto accountDto=new AccountDto();
		accountDto.setAccountName("twitter");
		accountDto.setGroupName("");
		accountDto.setPassword("twitter");
		accountDto.setUrl("https://twitter.com");
		Group group=new Group();
		group.setGroupName("");
		Account account=new Account();
		account.setId(10);
		account.setAccountName("twitter");
		account.setPassword("YmFuYW5h");
		when(accountRepository.findAll()).thenReturn(new ArrayList<Account>());
		when(groupRepository.findByGroupName("social")).thenReturn(Optional.of(group));
		when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
		assertEquals("twitter", accountController.addAccount(accountDto).getAccountName());
		
	}
	
}
