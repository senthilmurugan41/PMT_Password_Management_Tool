package com.epam.pmt.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.epam.pmt.exception.UserException;

public class ObjectPoolTest {

	ObjectPool objectPool;

	@BeforeEach
	void setUp() {
		objectPool = new ObjectPool();
	}

	@Test
	void getUserBeanTest() throws UserException {
		String name = objectPool.getUserBean(1).getName();
		assertEquals("com.epam.pmt.ui.Register", name);
	}

	@Test
	void getUserBeanException() {
		UserException exception = assertThrows(UserException.class, () -> objectPool.getUserBean(10));
		assertEquals("Please Enter Valid Input", exception.getMessage());
	}

	@Test
	void getAccountBeanTest() throws UserException {
		String name = objectPool.getAccountBean(1).getName();
		assertEquals("com.epam.pmt.ui.CreateAccount", name);
	}

	@Test
	void getAccountBeanException() {
		UserException exception = assertThrows(UserException.class, () -> objectPool.getAccountBean(10));
		assertEquals("Please Enter Valid Input", exception.getMessage());
	}

	@Test
	void getUserBeanFactoryTest() {
		assertTrue(objectPool.getUserBeanFactory() instanceof Map);
	}

	@Test
	void getAccountBeanFactoryTest() {
		assertTrue(objectPool.getAccountBeanFactory() instanceof Map);
	}
}
