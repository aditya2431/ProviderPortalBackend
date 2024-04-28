package com.abhi.empanelment.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfHeaderFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		setResponseHeader(response);
		
		CsrfToken csrfToken=(CsrfToken) request.
				getAttribute(CsrfToken.class.getName());
		if(csrfToken!=null) {
			Cookie cookie=WebUtils.getCookie(request, "XSRF-TOKEN");
			String token=csrfToken.getToken();
			if(cookie==null || token!=null && !token.equals(cookie.getValue()))
			{
				cookie=new Cookie("XSRF-TOKEN",token);
				cookie.setPath("/");
				response.addCookie(cookie);
				
			}
				
		}
		filterChain.doFilter(request, response);
		
	}

	private void setResponseHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "providerportaluat.adityabirlahealth.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept, X-Requested-With, x-customer-header-1, x-customer-header-2");	
	}

}