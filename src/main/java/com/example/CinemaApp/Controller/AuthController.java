package com.example.CinemaApp.Controller;

import com.example.CinemaApp.Security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("cinema/auth")
public class AuthController {
	
	private final AuthService authService;
	
	
	@PostMapping("/login")
	public ResponseEntity<JWTResponseDto> login (@RequestBody LoginRequestDto loginRequest){
		
		JWTResponseDto jwtResponseDto = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
		
		return ResponseEntity.ok(jwtResponseDto);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegistrationRequestDto registrationRequest) {

		JWTResponseDto jwtResponseDto = authService.registerUser(registrationRequest);

		return ResponseEntity.ok(jwtResponseDto);
	}
	
	
	 @PostMapping("/refresh-token")
	 public ResponseEntity<AccessTokenDto> refreshAccessToken(@RequestParam String refreshToken) {
		 
		 AccessTokenDto dto = authService.refreshAccessToken(refreshToken);
		
		 return ResponseEntity.ok(dto);
	 }
	 
	 
	 @PostMapping("/logout")
	 public ResponseEntity<?> logout(@RequestParam String refreshToken) {

		 authService.logoutUser(refreshToken);

		 return ResponseEntity.ok(null);
	 }



}
