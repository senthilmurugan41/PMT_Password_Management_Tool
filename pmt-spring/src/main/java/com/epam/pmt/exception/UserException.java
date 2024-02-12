package com.epam.pmt.exception;

import com.epam.pmt.dto.ErrorDto;

public class UserException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final ErrorDto errorDto;

	public UserException(ErrorDto errorDto)
	{
		super(errorDto.getMessage());
		this.errorDto=errorDto;
		
	}

	public ErrorDto getErrorDto() {
		return errorDto;
	}
	
	

}
