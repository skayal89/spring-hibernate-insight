package com.learning.springmvc.service;

import java.util.List;

import com.learning.springmvc.model.UserRole;

public interface UserRoleService {
	UserRole findById(int id);
    UserRole findByType(String type);
    List<UserRole> findAll();
}
