package com.cvtopdf.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
	
	

	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	

	private final UserDetailsService userService;

	public SecurityConf(UserDetailsService userService) {
		this.userService = userService;
	}

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService);
	}
	
	
	

	protected void configure(HttpSecurity httpSec) throws Exception{
		
		
		
		httpSec
		.authorizeRequests()
		.regexMatchers(".*\\.css$").permitAll()
		.antMatchers("/registration").permitAll()
		.antMatchers("/db/**").permitAll()
				.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and()
	.formLogin()
	.loginPage("/login")
	.permitAll()
	.and()
	.logout()
	.logoutSuccessUrl("/login?logout")
	.permitAll();
		
		 httpSec.csrf().disable();
	        httpSec.headers().frameOptions().disable();
		
		
	}
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	
	
	 
	
}
