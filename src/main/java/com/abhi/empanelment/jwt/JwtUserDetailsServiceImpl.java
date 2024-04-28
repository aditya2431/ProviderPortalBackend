package com.abhi.empanelment.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhi.empanelment.jwt.UserJwtLogin;
import com.abhi.empanelment.jwt.UserJwtRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserJwtRepository userJwtRepository;
	
	@Value("${spring.jwt.passcode}")
	private String password;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserJwtLogin userJwtLogin=userJwtRepository.authenicateUserName(username);
		if(userJwtLogin!=null) {
			throw new UsernameNotFoundException(String.format("No User Name Found with UserName '%s'", username));
		}
		return JwtuserFactory.create(userJwtLogin, "Admin",password, true);
	}

}
