package com.abhi.empanelment.jwt;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.abhi.empanelment.jwt.UserJwtLogin;

public class JwtuserFactory {

public static JwtUser create(UserJwtLogin userJwtLogin,String roleName,String password,boolean isEnabled) {
		
		return new JwtUser(userJwtLogin.getUserName(), password,  userJwtLogin, 
				maptoGrantedAuthorities(
						new ArrayList<String>(
								java.util.Arrays.asList
								("ROLE_"+roleName))),isEnabled);
	}
	
	private static List<GrantedAuthority> maptoGrantedAuthorities(List<String> authorities) {
		return authorities.stream()
		.map(Authority -> new 
				SimpleGrantedAuthority(Authority)).
		collect(Collectors.toList());
	}
}
