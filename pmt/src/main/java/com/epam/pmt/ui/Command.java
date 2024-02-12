package com.epam.pmt.ui;

import org.springframework.stereotype.Component;

import com.epam.pmt.exception.UserException;
@Component
public interface Command {
	public void execute() throws UserException;
}
