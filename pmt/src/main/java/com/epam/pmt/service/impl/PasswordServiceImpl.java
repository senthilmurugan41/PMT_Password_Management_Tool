package com.epam.pmt.service.impl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.epam.pmt.service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService{

	@Override
	public String encrypt(String password) {
		return Base64.getEncoder().encodeToString(password.getBytes());
	}

	@Override
	public String decrypt(String password) {
		byte[] decodedByte=Base64.getDecoder().decode(password);
		return new String(decodedByte);
	}

}
