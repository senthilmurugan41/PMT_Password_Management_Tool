package com.epam.pmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dao.UserDAO;
import com.epam.pmt.entity.User;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	public UserServiceImpl()
	{
	
	}
	

	public UserServiceImpl(UserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}


	@Override
	public boolean login(String password) throws UserException {
		userDAO.getUser(password);
		return true;
	}

	@Override
	public boolean register(String userName, String password, String email) throws UserException {
		
		User user=new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		List<User> users=userDAO.getUser();
		if(!users.isEmpty())
			throw new UserException("User already present!!!");
		userDAO.addUser(user);
		return true;
		
	}

}
