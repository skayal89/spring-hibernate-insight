package com.learning.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/* Follow below tutorial
 * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 * http://www.concretepage.com/spring-4/spring-mvc-4-security-hibernate-5-mysql-using-annotation-xml-example
 * */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	PersistentTokenRepository persistentTokenRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.antMatchers("/","/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/new-user", "/delete-user/*").access("hasRole('ADMIN')")
				.antMatchers("/edit-user/*").access("hasRole('ADMIN') or hasRole('USER')")
				.and()
				.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.usernameParameter("ssoid") // must be same as name parameter of <input>
					.passwordParameter("password") // must be same as name parameter of <input>
					.defaultSuccessUrl("/list")
				.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/logout")
				.and()
				.rememberMe()
					.rememberMeParameter("remember-me") // must be same as name parameter of <input>
					.tokenRepository(persistentTokenRepository) 
					.tokenValiditySeconds(46800)
				.and().csrf()
				.and().exceptionHandling().accessDeniedPage("/access_denied");
	}
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(userDetailsService);
		builder.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * PersistentTokenBasedRememberMeServices will be Autowired in AppController.
	 * */
	@Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, persistentTokenRepository);
        return tokenBasedservice;
    }
 
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
