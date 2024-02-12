package com.epam.pmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dao.GroupDAO;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupDAO groupDAO;
	
	
	public GroupServiceImpl()
	{
	

	}
	
	
	
	public GroupServiceImpl(GroupDAO groupDAO) {
		super();
		this.groupDAO = groupDAO;
	}



	@Override
	public List<Group> displayAllGroup() throws UserException {
		return groupDAO.readGroup();
	}

	@Override
	public boolean deleteGroup(String groupName) throws UserException {
	 return groupDAO.removeGroup(groupName);
	}

	@Override
	public boolean updateGroup(String groupName, String newGroupName) throws UserException {
		return groupDAO.updateGroup(groupName, newGroupName);
	}

}
