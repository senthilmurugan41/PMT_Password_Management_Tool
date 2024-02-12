package com.epam.pmt.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.UserService;

@Component
public class Register implements Command {

	
	private Scanner cin;
	@Autowired
	private UserService userService;
	private static final Logger LOGGER=LogManager.getLogger(Register.class);
	
	public  Register()
	{
		cin=new Scanner(System.in);
	}
	@Override
	public void execute() throws UserException {
		 
		LOGGER.info("Enter the User Name");
		String userName=cin.nextLine();
		LOGGER.info("Enter the password");
		String password=cin.nextLine();
		LOGGER.info("Enter the Email");
		String email=cin.nextLine();
			userService.register(userName,password,email);
			LOGGER.info("User Registered Sucessfully!!!");
	}

}
