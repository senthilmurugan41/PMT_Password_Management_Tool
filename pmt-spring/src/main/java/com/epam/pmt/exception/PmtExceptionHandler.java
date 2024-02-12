package com.epam.pmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.pmt.dto.ErrorDto;

@ControllerAdvice
public class PmtExceptionHandler {

	@ExceptionHandler(UserException.class)
	@ResponseBody
	public ResponseEntity<ErrorDto> handlerExerciseServiceException(UserException userException) {
		ErrorDto errorDto = userException.getErrorDto();
		HttpStatus httpStatus = getHttpStatus(errorDto);
		return new ResponseEntity<>(errorDto,httpStatus);
	}
	
	private HttpStatus getHttpStatus(ErrorDto errorDto) {
		HttpStatus httpStatus = HttpStatus.PARTIAL_CONTENT;
		if(errorDto.getErrorCode().startsWith("400") || errorDto.getErrorCode().startsWith("APP")) {
			httpStatus = HttpStatus.BAD_REQUEST;
		} else if(errorDto.getErrorCode().startsWith("403")) {
			httpStatus = HttpStatus.FORBIDDEN;
		} else if(errorDto.getErrorCode().startsWith("500")) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return httpStatus;
	}
}
