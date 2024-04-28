package com.abhi.empanelment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.empanelment.jwt.JwtUser;
import com.abhi.empanelment.jwt.UserJwtLoginDTO;
import com.abhi.empanelment.repository.TokenRepository;
import com.abhi.empanelment.utility.JwtTokenUtil;
import com.abhi.empanelment.model.Token;

@RestController
@RequestMapping({ "/api" })
public class AuthenticationContoller {

	@Value("${spring.jwt.authorization}")
	private String tokenHeader; // HeaderName="Authorization"

	@Value("${spring.jwt.password}")
	private String password;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil; // Wraper to JWTToken

	@Autowired
	TokenRepository tokenRepository;

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UserJwtLoginDTO userDto, HttpServletRequest request,
			HttpServletResponse response) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), password));// user.getPassword()
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenUtil.generateToken(jwtUser);

		revokeAlltokenByUserName(jwtUser);
		saveUserToken(jwtUser, token); // for logout;

		response.setHeader(tokenHeader, token);
		return ResponseEntity.ok("Sueesfully Genraed");
	}

	public void revokeAlltokenByUserName(JwtUser jwtUser) {
		List<Token> validtokenListByUserName = tokenRepository.findAllTokenByUserName(jwtUser.getUsername());
		if (!validtokenListByUserName.isEmpty()) {
			validtokenListByUserName.forEach(x -> {
				x.setLogout(true);
			});
		}
		tokenRepository.saveAll(validtokenListByUserName);
	}

	public void saveUserToken(JwtUser jwtUser, String token) {
		Token tokenModel = new Token();
		tokenModel.setLogout(false);
		tokenModel.setToken(token);
		tokenModel.setUsername(jwtUser.getUsername());
		tokenRepository.save(tokenModel);
	}

}
