package com.epam.pmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.GroupDto;
import com.epam.pmt.exception.UserException;

@Service
public interface GroupService {
	public List<GroupDto> displayAllGroup() throws UserException;
	
	public GroupDto deleteGroup(String groupName) throws UserException;
	
	public GroupDto addGroup(GroupDto groupDto) throws UserException;
	
	public GroupDto updateGroup(String groupName,int id) throws UserException;
	
	public List<AccountDto> readAccount(String groupName) throws UserException;
	
	public long getGroupCount();
	
}
