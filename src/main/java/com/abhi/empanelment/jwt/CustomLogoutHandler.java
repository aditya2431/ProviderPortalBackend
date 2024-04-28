package com.abhi.empanelment.jwt;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.abhi.empanelment.model.Token;
import com.abhi.empanelment.repository.TokenRepository;

@Component
public class CustomLogoutHandler implements LogoutHandler{

	@Value("${spring.jwt.authorization}")
	private String authorization;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		String authToken=Optional.ofNullable(request.getHeader(this.authorization)).orElse("");
		Token soredToken=tokenRepository.findByToken(authToken).orElse(null);
		if(authToken!=null) {
			soredToken.setLogout(true);
			tokenRepository.save(soredToken);
		}
		
	}
	
}
