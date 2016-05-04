package com.seavus.twitter.config.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.seavus.twitter.services.UserRepositoryUserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class WebSequrityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth,
			UserDetailsService userDetailsService )throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		 http
         .authorizeRequests()
         	.antMatchers("/resources/**").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin()
         	.loginPage("/login")
         	.permitAll()
         .and()
         	.logout()
         	.permitAll();
	}
}
