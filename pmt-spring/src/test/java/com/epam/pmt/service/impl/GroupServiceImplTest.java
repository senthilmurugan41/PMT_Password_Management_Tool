package com.epam.pmt.service.impl;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.repository.GroupRepository;

@SpringBootTest
class GroupServiceImplTest {
	
	@Autowired
	private GroupServiceImpl groupServiceImpl;
	
	@MockBean
	private GroupRepository groupRepository;
	
	@Test
	void deleteGroup()
	{
		when(groupRepository.findByGroupName("java")).thenReturn(Optional.empty());
		assertThrows(UserException.class, ()->groupServiceImpl.deleteGroup("java"));
	}
	
	@Test
	void updateGroup()
	{
		when(groupRepository.findByGroupName("java")).thenReturn(Optional.of(new Group()));
		assertThrows(UserException.class, ()->groupServiceImpl.updateGroup("java",10));
	}
	
	@Test
	void readAccount()
	{
		when(groupRepository.findByGroupName("java")).thenReturn(Optional.empty());
		assertThrows(UserException.class, ()->groupServiceImpl.readAccount("java"));
	}
	
	

}
