package com.epam.pmt.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.AccountService;

@Component
public class CreateAccount implements Command {
	
	@Autowired
	private AccountService accountService;
	
	private Scanner cin;
	
	private static final Logger LOGGER=LogManager.getLogger(CreateAccount.class);
	
	public CreateAccount()
	{
		cin=new Scanner(System.in);
		
	}

	@Override
	public void execute() {
		Account account=new Account();
		LOGGER.info("Enter the Account Name");
		account.setAccountName(cin.nextLine());
		LOGGER.info("Enter the Account URL");
		account.setUrl(cin.nextLine());
		LOGGER.info("Enter the password");
		account.setPassword(cin.nextLine());
		LOGGER.info("Enter the Group");
		String s=cin.nextLine();
		account.setAccountGroup(s);
		try {
			accountService.createPassword(account);
			LOGGER.info("Password Created Successfully!!!");
		} catch (UserException e) {
			LOGGER.error(e.getMessage());
		}
		
	}

}
