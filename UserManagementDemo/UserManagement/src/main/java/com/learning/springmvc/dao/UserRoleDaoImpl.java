package com.learning.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.learning.springmvc.model.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDao<Integer, UserRole> implements UserRoleDao {

	public UserRole findById(int id) {
		UserRole userRole=getByKey(id);
		return userRole;
	}

	public UserRole findByType(String type) {
		Criteria criteria=createEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		return ((UserRole) criteria.uniqueResult());
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findAll() {
		Criteria criteria=createEntityCriteria();
		criteria.addOrder(Order.asc("type"));
		return (List<UserRole>)criteria.list();
	}

}
