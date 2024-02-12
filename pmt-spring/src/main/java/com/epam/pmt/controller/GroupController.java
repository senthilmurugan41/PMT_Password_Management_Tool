package com.epam.pmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.GroupDto;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.GroupService;

@RestController
@CrossOrigin
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	
	@GetMapping("/readGroup")
	public @ResponseBody List<GroupDto> getGroup() throws UserException
	{
		return groupService.displayAllGroup();
	}
	
	@GetMapping("/displayAccount/{groupName}")
	public @ResponseBody List<AccountDto> getAccount(@PathVariable String groupName) throws UserException
	{
		return groupService.readAccount(groupName);
	}
	
	@PostMapping("/addGroup")
	public @ResponseBody GroupDto addGroup(@RequestBody GroupDto groupDto) throws UserException
	{
		return groupService.addGroup(groupDto);
	}
	
	
	@DeleteMapping("deleteGroup/{groupName}")
	public @ResponseBody GroupDto removeGroup(@PathVariable String groupName) throws UserException
	{
		return groupService.deleteGroup(groupName);
	}
	
	@PutMapping("updateGroup/{groupName}/{id}")
	public @ResponseBody GroupDto updateGroup(@PathVariable String groupName, @PathVariable int id) throws UserException
	{
		return groupService.updateGroup(groupName, id);
	}
	@GetMapping("/getGroupCount")
	public long getGroupCount()
	{
		return groupService.getGroupCount();
	}
}
