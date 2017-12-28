package com.learning.springmvc.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.learning.springmvc.model.UserRole;
import com.learning.springmvc.service.UserRoleService;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class IdToUserRoleConverter implements Converter<Object, UserRole> {
	
	static final Logger logger = LoggerFactory.getLogger(IdToUserRoleConverter.class);
	
	@Autowired
	UserRoleService userRoleService;

	public UserRole convert(Object arg0) {
		Integer id=Integer.parseInt((String)arg0);
		UserRole userRole=userRoleService.findById(id);
		logger.info("UserRole : {}",userRole);
		return userRole;
	}

}
