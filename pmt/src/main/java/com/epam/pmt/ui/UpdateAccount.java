package com.epam.pmt.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.AccountService;

@Component
public class UpdateAccount implements Command {

	@Autowired
	private AccountService accountService;

	private Scanner cin;

	private static final Logger LOGGER=LogManager.getLogger(UpdateAccount.class);
	public UpdateAccount() {

		cin = new Scanner(System.in);


	}

	@Override
	public void execute() {
		LOGGER.info("Enter the AccountName");
		String accountName=cin.nextLine();
		LOGGER.info("Enter the Password");
		String password=cin.nextLine();
		try {
			accountService.updatePassword(accountName,password);
			LOGGER.info("Password Updated!!!");
		} catch (UserException e) {
			
			LOGGER.info(e.getMessage());
		}
	}

}
