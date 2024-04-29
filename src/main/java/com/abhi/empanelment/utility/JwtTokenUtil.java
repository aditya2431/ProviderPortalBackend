package com.abhi.empanelment.utility;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.abhi.empanelment.jwt.JwtUser;
import com.abhi.empanelment.repository.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.abhi.empanelment.model.Token;

@Component
public class JwtTokenUtil implements Serializable {

	
	static final String CLAM_KEY_USERNAME = "sub";
	//static final String CLAM_KEY_AUDIENCE = "audience";
	static final String CLAM_KEY_CREATED = "created";

	@Value("${spring.jwt.secret}")
	private String secret;

	@Value("${spring.jwt.expiration}")
	private Long expiration;

	@Autowired
	TokenRepository tokenRepository;
	
	public String getUserNameFromToken(String authToken) {

		String userName = null;
		try {
			final Claims cliams = getCliamsFromToken(authToken);
			userName = cliams.getSubject();

		} catch (Exception e) {
			userName = null;
		}

		return userName;
	}

	private Claims getCliamsFromToken(String authToken) {

		Claims claims = null;

		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();

		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public boolean validateToken(String authToken, UserDetails userDetails) {

		JwtUser user = (JwtUser) userDetails;
		final String username = getUserNameFromToken(authToken);
		boolean isValid= tokenRepository.findByToken(authToken).map(x->!x.isLogout()).orElse(false);
		System.out.println("Token logout value:"+isValid);
		return (username.equals(user.getUsername()) &&
				!isTokenExpired(authToken) && isValid);
//		return (username.equals(user.getUsername()) &&
//				!isTokenExpired(authToken));
	}

	private boolean isTokenExpired(String authToken) {
		final Date expiration = getExpirationDateFromToken(authToken);

		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String authToken) {
		Date expiration = null;

		try {

			final Claims claims = getCliamsFromToken(authToken);
			if (claims != null) {
				expiration = claims.getExpiration();
			}
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	public String generateToken(JwtUser userDetails) {

		Map<String,Object> claims=new HashMap<String, Object>();
		claims.put(CLAM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAM_KEY_CREATED, new Date());
		return tokenCreate(claims);


	}

	private String tokenCreate(Map<String, Object> claims) {

		return Jwts.builder().setClaims(claims).  //This will generate token
				setExpiration(generateExpirationDate()).//one week Expriation
				signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generateExpirationDate() {
		// TODO Auto-generated method stub
		return new Date(System.currentTimeMillis() + expiration*1000);
	}

}
