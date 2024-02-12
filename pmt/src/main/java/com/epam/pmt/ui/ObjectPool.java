package com.epam.pmt.ui;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;

@Component
public class ObjectPool {
	private Map<Integer, Class<?>> userBeanFactory;

	private Map<Integer, Class<?>> accountBeanFactory;

	public ObjectPool() {
		userBeanFactory = new HashMap<>();
		accountBeanFactory = new HashMap<>();
		userBeanFactory.put(1,  Register.class);
		userBeanFactory.put(2,  Login.class);
		accountBeanFactory.put(1, CreateAccount.class);
		accountBeanFactory.put(2, ReadPassword.class);
		accountBeanFactory.put(3, DisplayGroup.class);
		accountBeanFactory.put(4, DisplayAccount.class);
		accountBeanFactory.put(5, DeleteAccount.class);
		accountBeanFactory.put(6, UpdateAccount.class);
		accountBeanFactory.put(7, UpdateGroup.class);
		accountBeanFactory.put(8, DeleteGroup.class);
	}

	public Class<?> getUserBean(int index) throws UserException {
		if (!userBeanFactory.containsKey(index))
			throw new UserException("Please Enter Valid Input");
		return userBeanFactory.get(index);
	}

	public Class<?> getAccountBean(int index) throws UserException {
		if (!accountBeanFactory.containsKey(index))
			throw new UserException("Please Enter Valid Input");
		return accountBeanFactory.get(index);
	}

	public Map<Integer, Class<?>> getUserBeanFactory() {
		return userBeanFactory;
	}

	public void setUserBeanFactory(Map<Integer, Class<?>> userBeanFactory) {
		this.userBeanFactory = userBeanFactory;
	}

	public Map<Integer, Class<?>> getAccountBeanFactory() {
		return accountBeanFactory;
	}

	public void setAccountBeanFactory(Map<Integer, Class<?>> accountBeanFactory) {
		this.accountBeanFactory = accountBeanFactory;
	}

}
