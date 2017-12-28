package com.learning.springmvc.dao;

import java.util.List;

import com.learning.springmvc.model.UserRole;

public interface UserRoleDao {
	UserRole findById(int id);
	UserRole findByType(String type);
	List<UserRole> findAll();
}
