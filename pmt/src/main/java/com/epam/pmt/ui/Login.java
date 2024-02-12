package com.epam.pmt.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.UserService;

@Component
public class Login implements Command {
	
	@Autowired
	private UserService userService;
	private Scanner cin;
	private static final Logger LOGGER=LogManager.getLogger(Login.class);
	public Login()
	{
		cin=new Scanner(System.in);
	}

	@Override
	public void execute() {
		LOGGER.info("Enter the passoword");
		String password=cin.nextLine();
		try {
			userService.login(password);
		} catch (UserException e) {
			execute();
		}
			
	}

}
