package com.hit.dao;

import java.util.List;

import com.hit.model.Customer;

public interface CustomerDAO {
	void add(Customer customer);
	void delete(String id);
	Customer getCustomer(String id);
	List<Customer>	getAllCustomers();
	void update(Customer customer);
}
