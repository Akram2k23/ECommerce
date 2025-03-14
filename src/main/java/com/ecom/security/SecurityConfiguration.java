package com.ecom.security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ecom.userdetailsservice.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and(). csrf().disable()
		 
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/ss/saveuser", "/ss/saverole", "/ss/saveadmin", "/ss/loginadmin", "/ss/loginuser").permitAll() // Permit POST requests to "/saveuser"
			.antMatchers(HttpMethod.GET, "/ss/getAllCategory", "/ss/getAllProducts", "/ss/getProductById/{id}").permitAll()
			.antMatchers(HttpMethod.GET, "/uploads/**").permitAll()
			
//            .antMatchers(HttpMethod.GET, "/user/getuser/**").permitAll() // Permit GET requests to "/getuser/{id}"
//			.antMatchers("/ss/saverole").hasRole("Role_Admin")
//			.antMatchers(HttpMethod.GET, "/ss/getuser/**").hasRole("Role_Admin")
//			.antMatchers(HttpMethod.GET, "/ss/getuser/**").hasRole("Role_User")
//			.antMatchers(HttpMethod.DELETE, "/ss/deleteuser/**").hasRole("Role_Admin")
			
			.anyRequest().authenticated()
			.and().httpBasic();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
}
