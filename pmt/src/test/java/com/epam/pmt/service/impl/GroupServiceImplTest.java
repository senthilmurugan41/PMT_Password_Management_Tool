package com.epam.pmt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.epam.pmt.dao.impl.GroupJPAImpl;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.GroupService;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GroupServiceImplTest {
	
	GroupService groupService;
	
	@Mock
	private GroupJPAImpl groupDao;
	
	@BeforeEach
	void setUp()
	{
		groupService=new GroupServiceImpl();
		groupService=new GroupServiceImpl(groupDao);
	}
	
	@Test
	@Order(1)
	void displayGroup() throws UserException
	{
		Group group=new Group();
		group.setGroupName("social");
		List<Group> expectedGroup=new ArrayList<>();
		expectedGroup.add(group);
		when(groupDao.readGroup()).thenReturn(expectedGroup);
		List<Group> groups=groupService.displayAllGroup();
		
		assertEquals("social", groups.get(0).getGroupName());
		
	}
	
	@Test
	@Order(2)
	void deleteGroup() throws UserException 
	{
		when(groupDao.removeGroup(anyString())).thenReturn(true);
		assertTrue(groupService.deleteGroup("social"));
	}
	
	@Test
	@Order(3)
	void updateGroup() throws UserException
	{
		when(groupDao.updateGroup(anyString(), anyString())).thenReturn(true);
		assertTrue(groupService.updateGroup("app", "apps"));
	}
	
//	@Test
//	void updateGroupException()
//	{
//		Exception exception=assertThrows(UserException.class, ()->groupService.updateGroup("java", "python"));
//		
//		assertEquals("Group Not Found", exception.getMessage());
//	}
//	
//	@Test
//	void readGroupException()
//	{
//		GroupDAO groupDAO=GroupDAOImpl.getInstance();
//		Exception exception=assertThrows(UserException.class, ()->groupDAO.readGroup("java"));
//		
//		assertEquals("Group Not Found!!!", exception.getMessage());
//	}
//	
//	@Test
//	void removeGroupException()
//	{
//		Exception exception=assertThrows(UserException.class, ()->groupService.deleteGroup("java"));
//		
//		assertEquals("Group Not Found", exception.getMessage());
//	}
//	
//	
	
	
	

}
