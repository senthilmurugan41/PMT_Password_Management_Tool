package com.epam.pmt.ui;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.AccountService;

@Component 
public class DisplayAccount implements Command {
	
	@Autowired
	private AccountService accountService;
	
	private Scanner cin;
	
	private static final Logger LOGGER=LogManager.getLogger(DisplayAccount.class);
	public DisplayAccount()
	{

		cin=new Scanner(System.in);
	}

	@Override
	public void execute() {
		LOGGER.info("Enter the Group Name");
		try {
			List<Account> accounts=accountService.displayAllAccount(cin.nextLine());
			for(Account account:accounts)
			{
				LOGGER.info("Account Name : {}", account.getAccountName());
				LOGGER.info("Account URL  : {}",account.getUrl());
				LOGGER.info("Account Password: {}",account.getPassword());
			}
		} catch (UserException e) {
			LOGGER.error(e.getMessage());
		}

	}

}
