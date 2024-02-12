package com.epam.pmt.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.AccountService;

@Component
public class DeleteAccount implements Command{

	@Autowired
	private AccountService accountService;
	
	private Scanner cin;
	
	private static final Logger LOGGER=LogManager.getLogger(DeleteAccount.class);
	public DeleteAccount()
	{
		cin=new Scanner(System.in);
	}
	@Override
	public void execute() {
		LOGGER.info("Enter the AccountName");
		try {
			accountService.removeAccount(cin.nextLine());
			LOGGER.info("Account has been Deleted successfully!!!");
		} catch (UserException e) {
			LOGGER.error(e.getMessage());
		}
	}

}
