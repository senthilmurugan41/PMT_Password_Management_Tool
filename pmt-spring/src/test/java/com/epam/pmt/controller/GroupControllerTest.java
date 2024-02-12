package com.epam.pmt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.GroupDto;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.repository.GroupRepository;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class GroupControllerTest {
	
	
	@MockBean
	private GroupRepository groupRepository;
	
	@Autowired
	private GroupController groupController;
	
	@Test
	@Order(1)
	void getGroupTest() throws UserException
	{
	  List<Group> groups=new ArrayList<>();
	  Group group=new Group();
	  group.setGroupName("Java");
	  groups.add(group);
	  when(groupRepository.findAll()).thenReturn(groups);
	  assertEquals(1,groupController.getGroup().size());
	}
	
	@Test
	@Order(2)
	void getAccount() throws UserException {
		Group group=new Group();
		group.setGroupName("java");
		List<Account> accounts=new ArrayList<>();
		Account account = new Account();
		account.setAccountName("apple");
		account.setPassword("YmFuYW5h");
		account.setUrl("https://apple.com");
		accounts.add(account);
		group.setAccounts(accounts);
		when(groupRepository.findByGroupName(group.getGroupName())).thenReturn(Optional.of(group));
		assertEquals(1,groupController.getAccount(group.getGroupName()).size());
		
	}
	
	@Test
	@Order(3)
	void addGroupTest() throws UserException
	{
		  List<Group> groups=new ArrayList<>();
		  Group group=new Group();
		  group.setGroupName("Java");
		  groups.add(group);
		  Group group1=new Group();
		  group1.setGroupName("Python");
		  GroupDto groupDto=new GroupDto();
		  groupDto.setGroupName("Python");
		  List<AccountDto> accountDtos=new ArrayList<>();
		  AccountDto accountDto=new AccountDto();
		  accountDto.setAccountName("JAVA");
		  accountDto.setGroupName("Java");
		  accountDto.setPassword("java");
		  accountDto.setUrl("https://java.com");
		  accountDtos.add(accountDto);
		  groupDto.setAccounts(accountDtos);
		  when(groupRepository.findAll()).thenReturn(groups);
		  when(groupRepository.save(group1)).thenReturn(group1);
		  assertEquals(group1.getGroupName(), groupController.addGroup(groupDto).getGroupName());
		
	}
	@Test
	@Order(4)
	void removeGroup() throws UserException
	{
		Group group=new Group();
		group.setGroupName("Java");
		when(groupRepository.findByGroupName("Java")).thenReturn(Optional.of(group));
		assertEquals("Java",groupController.removeGroup(group.getGroupName()).getGroupName());
		
	}
	@Test
	@Order(5)
	void updateGroup() throws UserException
	{
		Group group=new Group();
		group.setGroupName("Java");
		when(groupRepository.findByGroupName("Java")).thenReturn(Optional.empty());
		when(groupRepository.findById(2)).thenReturn(Optional.of(group));
		when(groupRepository.save(group)).thenReturn(group);
		assertEquals("Java", groupController.updateGroup("Java", 2).getGroupName());
		
	}
	
	@Test
	@Order(6)
	void getGroupCount()
	{
		
		when(groupRepository.count()).thenReturn(3l);
		assertEquals(3l,groupController.getGroupCount());
	}
	
	@Test
	void addGroupUnassignedTest()
	{
		  List<Group> groups=new ArrayList<>();
		  Group group=new Group();
		  group.setGroupName("");
		  groups.add(group);
		  Group group1=new Group();
		  group1.setGroupName("Python");
		  GroupDto groupDto=new GroupDto();
		  groupDto.setGroupName("");
		  List<AccountDto> accountDtos=new ArrayList<>();
		  AccountDto accountDto=new AccountDto();
		  accountDto.setAccountName("JAVA");
		  accountDto.setGroupName("");
		  accountDto.setPassword("java");
		  accountDto.setUrl("https://java.com");
		  accountDtos.add(accountDto);
		  groupDto.setAccounts(accountDtos);
		  when(groupRepository.findAll()).thenReturn(groups);
		  when(groupRepository.save(group1)).thenReturn(group1);
		  assertThrows(UserException.class, ()-> groupController.addGroup(groupDto));
		
	}
}
