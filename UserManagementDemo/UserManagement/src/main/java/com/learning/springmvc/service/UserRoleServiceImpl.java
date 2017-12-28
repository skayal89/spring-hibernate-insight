package com.learning.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.springmvc.dao.UserRoleDao;
import com.learning.springmvc.model.UserRole;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao userRoleDao;
	
	public UserRole findById(int id) {
		return userRoleDao.findById(id);
	}

	public UserRole findByType(String type) {
		return userRoleDao.findByType(type);
	}

	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

}
