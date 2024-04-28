package com.abhi.empanelment.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private JWTAuthenticationEntryPoint authenticationEntryPoint;

		@Autowired
		private CustomLogoutHandler logoutHandler; 
		
		@Autowired
		public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.
			userDetailsService(userDetailsService).
			passwordEncoder(PasswordEncoder());
		}

		@Bean
		public PasswordEncoder PasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception{
			
			//Creating our own Authentication Manager 
			//becuase when we send token it should create authenication Object
			return super.authenticationManagerBean();
		}
		
		@Bean
		public AuthenticationTokenFilter authenticationTokenFilterBean()
		{
			//Creating Our own Once per RequestFilter ==>
			//As framework does not know about encryption and decryption
			return new AuthenticationTokenFilter();
		}
		
		
		@Override
		protected void configure(HttpSecurity httpsecurity) throws Exception{
			
			//login ==> Should not authorize
			//Except them every thing should authorize
			httpsecurity
			.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll()
			.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
			.anyRequest().authenticated()
			.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			
			
			
			//The following code is nto required as its used for angular integration
			httpsecurity.addFilterBefore(authenticationTokenFilterBean(), 
					UsernamePasswordAuthenticationFilter.class)
			.logout(l->l.logoutUrl("/logout")
					.addLogoutHandler(logoutHandler)
					.logoutSuccessHandler((request,response,authenication)
							->SecurityContextHolder.clearContext()
							))
			.addFilterAfter(new CsrfHeaderFilter(),CsrfFilter.class);
			
			httpsecurity.headers().cacheControl();
			httpsecurity.headers().httpStrictTransportSecurity().
			includeSubDomains(true).maxAgeInSeconds(31536000);
			
			
		}
		
	}

