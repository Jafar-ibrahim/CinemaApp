package com.example.CinemaApp.Security;

import com.example.CinemaApp.Entity.Role;
import com.example.CinemaApp.Entity.TokenInfo;
import com.example.CinemaApp.Entity.AppUser;
import com.example.CinemaApp.Service.RoleService;
import com.example.CinemaApp.Service.TokenInfoService;
import com.example.CinemaApp.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

	 private final UserService userService;

	 private final AuthenticationManager authManager;
	 
	 private final HttpServletRequest httpRequest;
	 
	 private final TokenInfoService tokenInfoService;
	 
	 private final JwtTokenUtils jwtTokenUtils;

	 private final RoleService roleService;

	public JWTResponseDto login(String email, String password) {
	        Authentication authentication = authManager.authenticate(
	        		new UsernamePasswordAuthenticationToken(email, password));
	        
	        log.debug("Valid userDetails credentials.");

	        AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();
	        
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        log.debug("SecurityContextHolder updated. [email={}]", email);
	        
	        
	        TokenInfo  tokenInfo = createLoginToken(email, userDetails.getId());

	        
	        return JWTResponseDto.builder()
	        		.accessToken(tokenInfo.getAccessToken())
	        		.refreshToken(tokenInfo.getRefreshToken())
	        		.build();
	}

	public JWTResponseDto registerUser(RegistrationRequestDto registrationRequest) {

		// check if the email is already taken
		if (userService.existsByEmail(registrationRequest.getEmail())) {
			throw new RuntimeException("Username is already taken.");
		}

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(roleService.findByName("user"));

		// create a new User entity and set user details
		AppUser newUser = new AppUser();
		newUser.setEmail(registrationRequest.getEmail());
		newUser.setPassword(registrationRequest.getPassword()); // Use a password encoder to hash the password
		newUser.setName(registrationRequest.getName());
		newUser.setRoles(userRoles);

		log.debug("Valid userDetails credentials.");

		// save the new user to the database
		AppUser savedUser = userService.save(newUser);

		log.debug("SecurityContextHolder updated. [email={}]", newUser.getEmail());

		// generate JWT tokens for the registered user
		TokenInfo tokenInfo = createLoginToken(savedUser.getEmail(), savedUser.getId());

		return JWTResponseDto.builder()
				.accessToken(tokenInfo.getAccessToken())
				.refreshToken(tokenInfo.getRefreshToken())
				.build();
	}
	 
	 
	 public TokenInfo createLoginToken(String userName, Long userId) {
			String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);
			InetAddress ip = null;
			try {
				ip = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String accessTokenId = UUID.randomUUID().toString();
			String accessToken = JwtTokenUtils.generateToken(userName, accessTokenId, false);
			log.info("Access token created. [tokenId={}]", accessTokenId);

			String refreshTokenId = UUID.randomUUID().toString();
			String refreshToken = JwtTokenUtils.generateToken(userName, refreshTokenId, true);
			log.info("Refresh token created. [tokenId={}]", accessTokenId);

			TokenInfo tokenInfo = new TokenInfo(accessToken, refreshToken);
			tokenInfo.setUser(new AppUser(userId));
			tokenInfo.setUserAgentText(userAgent);
			tokenInfo.setLocalIpAddress(ip.getHostAddress());
			tokenInfo.setRemoteIpAddress(httpRequest.getRemoteAddr());
			// tokenInfo.setLoginInfo(createLoginInfoFromRequestUserAgent());
			return tokenInfoService.save(tokenInfo);
		}
	 
	 
	 public AccessTokenDto refreshAccessToken(String refreshToken) {
	        if (jwtTokenUtils.isTokenExpired(refreshToken)) {
	            return null;
	        }
	        String userName = jwtTokenUtils.getUserNameFromToken(refreshToken);
	        Optional<TokenInfo> refresh = tokenInfoService.findByRefreshToken(refreshToken);
	        if (!refresh.isPresent()) {
	            return null;
	        }

	        return new AccessTokenDto(JwtTokenUtils.generateToken(userName, UUID.randomUUID().toString(), false));

	    }
	 
	 
	 public void logoutUser(String refreshToken) {
	        Optional<TokenInfo> tokenInfo = tokenInfoService.findByRefreshToken(refreshToken);
	        if (tokenInfo.isPresent()) {
	            tokenInfoService.deleteById(tokenInfo.get().getId());
	        }

	    }


}
