package com.abhi.empanelment.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.abhi.empanelment.utility.JwtTokenUtil;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Value("${spring.jwt.authorization}")
	private String authorization;
	
	public List<String> urlList=Collections.unmodifiableList(Arrays.asList("/login"));
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authToken=Optional.ofNullable(request.getHeader(this.authorization)).orElse("");
		String url=Optional.ofNullable(request.getRequestURI()).orElse("");
		
		Long urlCount=urlList.stream().filter(x->x.contains(url)).distinct().count();
		
		if(!authToken.isEmpty() && urlCount>0) {
			String userName=jwtTokenUtil.getUserNameFromToken(authToken);
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails  =userDetailsService.loadUserByUsername(userName); 
				boolean isValid=jwtTokenUtil.validateToken(authToken, userDetails);
				if(isValid) {
					UsernamePasswordAuthenticationToken authenticationToken=
							new UsernamePasswordAuthenticationToken(userName,null, userDetails.getAuthorities());
					request.setAttribute("tokenUser", userDetails.getUsername());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}
		filterChain.doFilter(request, response); 		
	}


}
