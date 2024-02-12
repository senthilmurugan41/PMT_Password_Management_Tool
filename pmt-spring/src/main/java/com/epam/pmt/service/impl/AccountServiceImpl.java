package com.epam.pmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.pmt.dto.AccountDto;
import com.epam.pmt.dto.ErrorDto;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.repository.AccountRepository;
import com.epam.pmt.repository.GroupRepository;
import com.epam.pmt.service.AccountService;
import com.epam.pmt.service.PasswordService;
import com.epam.pmt.service.ValidateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ValidateService validationService;

	@Autowired
	private PasswordService passwordService;


	@Override
	public AccountDto createAccount(AccountDto accountDto) throws UserException {
		Account account = convertToAccount(accountDto);
		if (validationService.validateUrl(account.getUrl())
				&& validationService.validateAccountList(accountRepository.findAll(), account)) {
			Optional<Group> groupOptional = groupRepository.findByGroupName(account.getAccountGroup().getGroupName());
			if (groupOptional.isPresent()) {
				Group group = groupOptional.get();
				group.getAccounts().add(account);
				account.setAccountGroup(group);
			}
			account = accountRepository.save(account);
		}
		return convertToAccountDto(account);
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
	public AccountDto updateAccount(AccountDto accountDto, int accountId) throws UserException {

		Optional<Account> optionalAccount = accountRepository.findById(accountId);
		if (!optionalAccount.isPresent())
		{
			ErrorDto errorDto=new ErrorDto();
			errorDto.setMessage("Account Not Found!!!");
			errorDto.setErrorCode("400103");
			throw new UserException(errorDto);
		}
		Account account = optionalAccount.get();
		account.getAccountGroup().setGroupName(accountDto.getGroupName());
		account.setAccountName(accountDto.getAccountName());
		account.setPassword(passwordService.encrypt(accountDto.getPassword()));
		account.setUrl(accountDto.getPassword());
		account = accountRepository.save(account);
		return convertToAccountDto(account);
	}

	@Override
	public AccountDto deleteAccount(int accountId) throws UserException {
		Optional<Account> accountOptional = accountRepository.findById(accountId);
		if (!accountOptional.isPresent())
		{
			ErrorDto errorDto=new ErrorDto();
			errorDto.setMessage("Account Id Not Present");
			errorDto.setErrorCode("400103");
			throw new UserException(errorDto);
		}
			
		accountRepository.delete(accountOptional.get());
		return convertToAccountDto(accountOptional.get());
	}
	@Override
	public List<AccountDto> getAllAccount() {
		List<Account> accounts=accountRepository.findAll();
		List<AccountDto> accountDtos=new ArrayList<>();
		accounts.forEach(account->accountDtos.add(convertToAccountDto(account)));
		return accountDtos;
	}
	@Override
	public long getAccountCount() {
		return accountRepository.count();
	}
}
