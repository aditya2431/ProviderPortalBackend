package com.abhi.empanelment.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abhi.empanelment.jwt.UserJwtLogin;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails{

	private final String username;
	private final String password;
	private final UserJwtLogin user;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	
	
	
	public JwtUser(String username, String password, UserJwtLogin user,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
		this.enabled = enabled;
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
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	
}
