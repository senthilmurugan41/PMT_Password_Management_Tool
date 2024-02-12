package com.epam.pmt.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;

@Repository
public interface GroupDAO {

	public Group addGroup(Group group);
	
	public Group readGroup(String groupName) throws UserException;
	
	public boolean updateGroup(String groupName,String newGroupName) throws UserException;
	
	public boolean removeGroup(String groupName) throws UserException;
	
	public List<Group> readGroup();
	
}
