package com.epam.pmt.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.epam.pmt.entity.Account;
import com.epam.pmt.exception.UserException;
import com.epam.pmt.service.ValidateService;

@Service
public class ValidateServiceImpl implements ValidateService{
	
	


	@Override
	public boolean validateAccount(Account account) throws UserException {
		
	  if (((account.getAccountName() != null)
                && (!account.getAccountName().equals(""))
                && (account.getAccountName().matches("^[a-zA-Z]*$"))))
		  return true;
	  else
		  throw new UserException("Not a valid Account");
	}

	@Override
	public boolean validatePassword(Account account) throws UserException {
		if(account.getPassword().length()<=64)
			return true;
		throw new UserException("Not a valid Password");
	}

	@Override
	public boolean validateAccountList(List<Account> accounts,Account account) throws UserException {
		for(Account acc:accounts)
		{
			if(acc.getAccountName().equals(account.getAccountName()))
			{
				throw new UserException("Account Already Present");
			}
		}
		return true;
		
	}

	@Override
	public boolean validateUrl(String url) throws UserException {
		String regex = "((http|https)://)(www.)?"
	              + "[a-zA-Z0-9@:%._\\+~#?&//=]"
	              + "{2,256}\\.[a-z]"
	              + "{2,6}\\b([-a-zA-Z0-9@:%"
	              + "._\\+~#?&//=]*)";
		
		 Pattern p = Pattern.compile(regex);
		 Matcher m = p.matcher(url);
		 if(m.matches())
			 return true;
		 throw new UserException("Not a valid Url");
		
	}


}
