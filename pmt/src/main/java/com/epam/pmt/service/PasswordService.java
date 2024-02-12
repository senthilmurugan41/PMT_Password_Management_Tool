package com.epam.pmt.service;

import org.springframework.stereotype.Service;

@Service
public interface PasswordService {

	public String encrypt(String password);
	
	public String decrypt(String password);
	
}
