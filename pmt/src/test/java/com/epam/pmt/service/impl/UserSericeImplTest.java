package com.epam.pmt.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.epam.pmt.dao.impl.UserJPAImpl;
import com.epam.pmt.entity.User;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.UserService;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
 class UserSericeImplTest {
	
	UserService userService;
	
	@Mock
	private UserJPAImpl userDao;
	@BeforeEach
	void setUp()
	{
		userService=new UserServiceImpl(userDao);
	}
	
	@Test
	@Order(1)
	void login() throws UserException 
	{
	   
		when(userDao.getUser()).thenReturn(new ArrayList<User>());
		assertTrue(userService.login("admin"));
	}
	
	@Test
	@Order(2)
	void register() throws UserException
	{
	
		when(userDao.getUser()).thenReturn(new ArrayList<User>());
		assertTrue(userService.register("senthil", "senthil", "senthil@epam.com"));
	}
	
	@Test
	@Order(3)
	void registerException() throws UserException
	{
		User user=new User();
		List<User> expectedUser=new ArrayList<>();
		expectedUser.add(user);
		when(userDao.getUser()).thenReturn(expectedUser);
		assertThrows(UserException.class,()->userService.register("senthil", "senthil", "senthil@epam.com"));
	}


}
