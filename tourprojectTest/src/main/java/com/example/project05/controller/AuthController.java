package com.example.project05.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project05.config.auth.PrincipalDetails;
import com.example.project05.controller.dto.CMRespDto;
import com.example.project05.controller.dto.SignupDto;
import com.example.project05.handler.ex.CustomValidationException;
import com.example.project05.model.user.User;
import com.example.project05.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;
	
	@GetMapping("/api/v1/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/api/v1/manager")
	public String manager() {
		return "manager";
	}
	
	@PostMapping("/auth/signup")
	public CMRespDto<?> signup(@RequestBody @Valid SignupDto signupDto, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			Map<String,String> errorMap = new HashMap<>();
			for(FieldError error:bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("===========================");
				System.out.println(error.getDefaultMessage());
				System.out.println("===========================");
			}
			throw new CustomValidationException("유효성 검사 실패",errorMap);
		} else {
			System.out.println(signupDto);
			User user = signupDto.toEntity();
			User userEntity = authService.회원가입(user);
			//System.out.println(userEntity);
			return new CMRespDto<>(1,"회원가입성공",userEntity);
		}
	}
	
}
