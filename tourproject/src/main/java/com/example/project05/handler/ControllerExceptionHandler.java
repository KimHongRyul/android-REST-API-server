package com.example.project05.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.project05.controller.dto.CMRespDto;
import com.example.project05.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto<?> validationException(CustomValidationException e) {
		return new CMRespDto<Map<String,String>>(-1,e.getMessage(),e.getErrorMap());
	}

}
