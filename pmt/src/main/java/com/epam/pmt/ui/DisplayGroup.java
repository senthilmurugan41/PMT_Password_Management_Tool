package com.epam.pmt.ui;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.GroupService;

@Component
public class DisplayGroup implements Command {
	
	@Autowired
	private GroupService groupService;
	private static final Logger LOGGER=LogManager.getLogger(DisplayGroup.class);
	

	@Override
	public void execute() {
		try {
			List<Group> groups=groupService.displayAllGroup();
			LOGGER.info("The Groups Are ");
			groups.forEach(i->LOGGER.info(i.getGroupName()));
		} catch (UserException e) {
			LOGGER.error(e.getMessage());
		}

	}

}
