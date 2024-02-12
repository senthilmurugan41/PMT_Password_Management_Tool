package com.epam.pmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;

@Service
public interface GroupService {
	public List<Group> displayAllGroup() throws UserException;
	
	public boolean deleteGroup(String groupName) throws UserException;
	
	public boolean updateGroup(String groupName,String newGroupName) throws UserException;
}
