package com.epam.pmt.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.epam.pmt.dto.ErrorDto;
import com.epam.pmt.entity.Account;
import com.epam.pmt.entity.Group;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.ValidateService;

@Service
public class ValidateServiceImpl implements ValidateService {

	@Override
	public boolean validateAccount(Account account) throws UserException {

		if (((account.getAccountName() != null) && (!account.getAccountName().equals(""))
				&& (account.getAccountName().matches("^[a-zA-Z]*$"))))
			return true;
		else {
			ErrorDto errorDto = new ErrorDto();
			errorDto.setMessage("Not a Valid Account");
			errorDto.setErrorCode("400104");
			throw new UserException(errorDto);
		}
	}

	@Override
	public boolean validatePassword(Account account) throws UserException {
		if (account.getPassword().length() <= 64)
			return true;
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage("Not a Valid Password");
		errorDto.setErrorCode("400105");
		throw new UserException(errorDto);
	}

	@Override
	public boolean validateAccountList(List<Account> accounts, Account account) throws UserException {
		for (Account acc : accounts) {
			if (acc.getAccountName().equals(account.getAccountName())) {
				ErrorDto errorDto = new ErrorDto();
				errorDto.setMessage("Account Id Not Present");
				errorDto.setErrorCode("400106");
				throw new UserException(errorDto);
			}
		}
		return true;

	}

	@Override
	public boolean validateUrl(String url) throws UserException {
		String regex = "((http|https)://)(www.)?" + "[a-zA-Z0-9@:%._\\+~#?&//=]" + "{2,256}\\.[a-z]"
				+ "{2,6}\\b([-a-zA-Z0-9@:%" + "._\\+~#?&//=]*)";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		if (m.matches())
			return true;
		ErrorDto errorDto = new ErrorDto();
		errorDto.setMessage("Not a valid Url");
		errorDto.setErrorCode("400103");
		throw new UserException(errorDto);

	}

	@Override
	public boolean validateGroupList(List<Group> groups, Group group) throws UserException {
		for (Group groupIter : groups) {
			if (groupIter.getGroupName().equalsIgnoreCase(group.getGroupName())) {
				ErrorDto errorDto = new ErrorDto();
				errorDto.setMessage("Group Already Found");
				errorDto.setErrorCode("400103");
				throw new UserException(errorDto);
			}
		}
		return true;
	}

}
