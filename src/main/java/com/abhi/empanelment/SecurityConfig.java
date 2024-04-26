package com.abhi.empanelment;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.headers().frameOptions().disable() // Disable X-Frame-Options for H2 Console
		.addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy", "script-src 'self'"))
		.xssProtection().block(true)// Enable XSS Protection;
//		.and().httpStrictTransportSecurity().disable();// Disable Strict Transport Security (HSTS) for development
		.and().httpStrictTransportSecurity()
		.maxAgeInSeconds(31536000) // Max age of HSTS policy in seconds
        .includeSubDomains(true);// enable Strict Transport Security (HSTS)
		//strict-transport-security aani x-frame-options disat nahiye
	}
}