package com.example.CinemaApp.Security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class LoginRequestDto {

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
}
