package com.epam.pmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.pmt.entity.User;
import com.epam.pmt.exception.UserException;

@Repository
public interface UserDAO {

	public boolean addUser(User user);
	
	public User getUser(String password) throws UserException;
	
	public List<User> getUser();
}
