package com.epam.pmt.ui;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.GroupService;

@Component
public class UpdateGroup implements Command {
	
	@Autowired
	private GroupService groupService;
	
	private Scanner cin;
	
	private static final Logger LOGGER=LogManager.getLogger(UpdateGroup.class);
	
	public UpdateGroup()
	{
		cin=new Scanner(System.in);
		
	}

	@Override
	public void execute() {
		LOGGER.info("Enter the Group");
		String oldGroup=cin.nextLine();
		LOGGER.info("Enter the updated Group Name");
		String newGroup=cin.nextLine();
		try {
			groupService.updateGroup(oldGroup, newGroup);
			LOGGER.info("Group Updated Successfully!!!");
		} catch (UserException e) {
			LOGGER.info(e.getMessage());
		}
		
	}

}
