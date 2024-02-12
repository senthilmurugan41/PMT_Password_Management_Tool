package com.epam.pmt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.ErrorDto;
import com.epam.pmt.dto.GroupDto;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.repository.GroupRepository;
import com.epam.pmt.service.GroupService;
import com.epam.pmt.service.PasswordService;
import com.epam.pmt.service.ValidateService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private ValidateService validateService;

	@Override
	public GroupDto addGroup(GroupDto groupDto) throws UserException {
		Group group = convertToGroup(groupDto);
		if (validateService.validateGroupList(groupRepository.findAll(), group)) {
			groupRepository.save(group);
		}
		return convertToGroupDto(group);
	}

	@Override
	public List<GroupDto> displayAllGroup() throws UserException {
		List<Group> groups = groupRepository.findAll();
		List<GroupDto> groupDtos = new ArrayList<>();
		groups.forEach(group -> groupDtos.add(convertToGroupDto(group)));
		return groupDtos;
	}

	@Override
	public GroupDto deleteGroup(String groupName) throws UserException {
		Optional<Group> optionalGroup = groupRepository.findByGroupName(groupName);
		if (!optionalGroup.isPresent()) {
			ErrorDto errorDto=new ErrorDto();
			errorDto.setMessage("Group Not Found");
			errorDto.setErrorCode("400102");
			throw new UserException(errorDto);
		}
		Group group = optionalGroup.get();
		groupRepository.delete(group);
		return convertToGroupDto(group);
	}

	@Override
	public GroupDto updateGroup(String groupName, int id) throws UserException {
		Optional<Group> optionalGroupByName = groupRepository.findByGroupName(groupName);
		Optional<Group> optionalGroup = groupRepository.findById(id);
		if (optionalGroupByName.isPresent()) {
			ErrorDto errorDto=new ErrorDto();
			errorDto.setMessage("Group Already Exist!!!");
			errorDto.setErrorCode("400103");
			throw new UserException(errorDto);
		}
		Group group = null;
		if (optionalGroup.isPresent()) {
			group = optionalGroup.get();
			group.setGroupName(groupName);
			groupRepository.save(group);
		}
		return convertToGroupDto(group);
	}

	@Override
	public List<AccountDto> readAccount(String groupName) throws UserException {
		Optional<Group> optionalGroup = groupRepository.findByGroupName(groupName);
		if (!optionalGroup.isPresent()) {
			ErrorDto errorDto=new ErrorDto();
			errorDto.setMessage("Group Not Found!!!");
			errorDto.setErrorCode("400104");
			throw new UserException(errorDto);
		}
		List<AccountDto> accountDtos = new ArrayList<>();
		Group group = optionalGroup.get();
		group.getAccounts().forEach(account -> accountDtos.add(convertToAccountDto(account)));
		return accountDtos;
	}

	public GroupDto convertToGroupDto(Group group) {
		GroupDto groupDto = new GroupDto();
		groupDto.setId(group.getId());
		groupDto.setGroupName(group.getGroupName());
		List<AccountDto> accountDtos = new ArrayList<>();
		group.getAccounts().forEach(account -> accountDtos.add(convertToAccountDto(account)));
		groupDto.setAccounts(accountDtos);
		return groupDto;
	}

	public Group convertToGroup(GroupDto groupDto) {
		Group group = new Group();
		group.setId(groupDto.getId());
		group.setGroupName(groupDto.getGroupName());
		if (groupDto.getAccounts() != null) {
			List<Account> accounts = new ArrayList<>();
			groupDto.getAccounts().forEach(account -> accounts.add(convertToAccount(account)));
			group.setAccounts(accounts);
		}
		return group;
	}

	public AccountDto convertToAccountDto(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setId(account.getId());
		accountDto.setAccountName(account.getAccountName());
		accountDto.setGroupName(account.getAccountGroup().getGroupName());
		accountDto.setPassword(passwordService.decrypt(account.getPassword()));
		accountDto.setUrl(account.getUrl());
		return accountDto;
	}

	public Account convertToAccount(AccountDto accountDto) {
		Account account = new Account();
		account.setId(accountDto.getId());
		if (accountDto.getGroupName().isEmpty())
			account.getAccountGroup().setGroupName("Unassigned");
		else {
			account.getAccountGroup().setGroupName(accountDto.getGroupName());
		}
		account.setAccountName(accountDto.getAccountName());
		account.setPassword(passwordService.encrypt(accountDto.getPassword()));
		account.setUrl(accountDto.getUrl());
		return account;
	}

	@Override
	public long getGroupCount() {
		return groupRepository.count();
	}

}
