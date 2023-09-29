package com.example.CinemaApp.Security;

import com.example.CinemaApp.Entity.AppUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@ToString
public class AppUserDetail implements UserDetails {
	
    private Long id ;
	
	private String email;
	
	private String password ; 
	
	private List<GrantedAuthority> authorities ;
	
    private boolean isEnabled;
	
	private boolean isCredentialsNonExpired;
	
	private boolean isAccountNonLocked;
	
	private boolean isAccountNonExpired;
	
	

	public AppUserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppUserDetail(AppUser user) {
		super();
		this.id= user.getId();
		this.email = user.getEmail();
		this.password= user.getPassword();
		this.isEnabled = user.isEnabled();
		this.isCredentialsNonExpired = user.isCredentialsNonExpired();
		this.isAccountNonExpired = user.isAccountNonExpired();
		this.isAccountNonLocked = user.isAccountNonLocked();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		 if(!user.getRoles().isEmpty()) {
		        	user.getRoles().forEach(role -> {
		        		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		        	});
		      }
		 this.authorities = authorities;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

}
