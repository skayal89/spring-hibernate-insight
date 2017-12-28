package com.learning.springmvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.springmvc.model.User;
import com.learning.springmvc.model.UserRole;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	static final String TAG=CustomUserDetailsService.class.getSimpleName();

	@Autowired
	UserService userService;
	
	@Transactional
	public UserDetails loadUserByUsername(String ssoid)
			throws UsernameNotFoundException {
		User user=userService.findBySSO(ssoid);
		logger.info(TAG+"/loadUserByUsername/User : {} ssoId: {}", user,ssoid);
		if(user==null){
			logger.info(TAG+"/loadUserByUsername/User not found");
			throw new UsernameNotFoundException("User not found");
		}
		boolean enabled=true;
		boolean accountNonExpired=true;
		boolean credentialsNonExpired=true;
		boolean accountNonLocked=true;
		return new org.springframework.security.core.userdetails.User(user.getSsoId(), 
				user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, 
				accountNonLocked, getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user) 	{
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		Set<UserRole> userRoles=user.getUserRoles();
		
		for(UserRole role : userRoles){
			logger.info(TAG+"/getGrantedAuthorities/UserRole : {}", role);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getType()));
			// user role has to start with ROLE_.
			// we can also store ROLE_ADMIN, ROLE_DBA directly in database.
		}
		logger.info(TAG+"/getGrantedAuthorities/authorities : {}", authorities);
		return authorities;
	}

}
