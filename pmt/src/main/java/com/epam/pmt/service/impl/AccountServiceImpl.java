package com.epam.pmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dao.AccountDAO;
import com.epam.pmt.dao.GroupDAO;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.AccountService;
import com.epam.pmt.service.PasswordService;
import com.epam.pmt.service.ValidateService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private GroupDAO groupDAO;
	
	@Autowired
	private ValidateService validateServcie;

	@Autowired
	private PasswordService passwordService;

	public AccountServiceImpl() {
		
	}
	public AccountServiceImpl(AccountDAO accountDAO, GroupDAO groupDAO, ValidateService validateServcie,
			PasswordService passwordService) {
		super();
		this.accountDAO = accountDAO;
		this.groupDAO = groupDAO;
		this.validateServcie = validateServcie;
		this.passwordService = passwordService;
	}



	@Override
	public boolean createPassword(Account account) throws UserException {
		boolean isCreated = false;
		if (validateServcie.validateUrl(account.getUrl())
				&& validateServcie.validateAccountList(accountDAO.getAccount(), account)) {
			account.setPassword(passwordService.encrypt(account.getPassword()));
			if (account.getAccountGroup().isEmpty())
				account.setAccountGroup("Unassigned");
			Group group = groupDAO.readGroup(account.getAccountGroup());
			if (group != null) {
				group.getAccounts().add(account);
				account.setGroup(group);
				groupDAO.addGroup(group);
			} else {
				group = new Group();
				group.setGroupName(account.getAccountGroup());
				account.setGroup(group);
				group.getAccounts().add(account);
				groupDAO.addGroup(group);
			}
			isCreated = true;
		}
		return isCreated;
	}

	@Override
	public Account readPasswordByAccount(String accountName) throws UserException {
		Account account= accountDAO.findAccountByName(accountName);
		account.setPassword(passwordService.decrypt(account.getPassword()));
		return account;
	}

	@Override
	public List<Account> displayAllAccount(String groupName) throws UserException {
		List<Account> accounts= accountDAO.findAccountByGroupName(groupName);
		for(Account account:accounts)
			account.setPassword(passwordService.decrypt(account.getPassword()));
		return accounts;
	}

	@Override
	public boolean removeAccount(String accountName) throws UserException {
		return accountDAO.removeAccount(accountName);
	}

	@Override
	public boolean updatePassword(String accountName, String password) throws UserException {
		boolean isUpdated = true;
		accountDAO.updateAccount(accountName, password);
		return isUpdated;
	}

}
