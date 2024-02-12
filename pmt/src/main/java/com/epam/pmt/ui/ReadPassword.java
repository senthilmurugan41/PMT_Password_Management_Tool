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
public class ReadPassword implements Command {
	
	@Autowired
	private AccountService accountService;
	private Scanner cin;
	private static final Logger LOGGER=LogManager.getLogger(ReadPassword.class);
	public ReadPassword()
	{
		cin=new Scanner(System.in);
	}

	@Override
	public void execute() {
		LOGGER.info("Enter the Account Name");
		try {
			Account account=accountService.readPasswordByAccount(cin.nextLine());
			LOGGER.info("Account Found!!!");
			LOGGER.info("Account Name    : {}",account.getAccountName());
			LOGGER.info("Account Password: {}",account.getPassword());
			LOGGER.info("Account Url     : {}",account.getUrl());
		} catch (UserException e) {
			LOGGER.error(e.getMessage());
		}

	}

}
