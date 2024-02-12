package com.epam.pmt.service;

import org.springframework.stereotype.Service;

import com.epam.pmt.exception.UserException;

@Service
public interface UserService {

	public boolean register(String userName,String password,String email) throws UserException;
	
	public boolean login(String password) throws UserException;
}
