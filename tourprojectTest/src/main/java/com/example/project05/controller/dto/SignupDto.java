package com.example.project05.controller.dto;

import javax.validation.constraints.NotBlank;

import com.example.project05.model.user.User;

import lombok.Data;

@Data
public class SignupDto {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
	}

}
