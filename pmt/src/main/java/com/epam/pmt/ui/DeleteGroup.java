package com.epam.pmt.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.GroupService;

@Component
public class DeleteGroup implements Command {
	@Autowired
	private GroupService groupService;
	
	private Scanner cin;
	private static final Logger LOGGER=LogManager.getLogger(DeleteGroup.class);
	public DeleteGroup()
	{
	
		cin=new Scanner(System.in);
	}

	@Override
	public void execute() {
		LOGGER.info("Enter the Group");
		try {
			groupService.deleteGroup(cin.nextLine());
			LOGGER.info("Group Deleted Successfully");
		} catch (UserException e) {
			LOGGER.error(e.getMessage());
		}
		
	}

}
