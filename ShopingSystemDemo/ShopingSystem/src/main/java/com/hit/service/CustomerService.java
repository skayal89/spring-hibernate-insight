package com.hit.service;

import java.util.List;

import com.hit.model.Customer;

public interface CustomerService {
	void add(Customer customer);
	void delete(String id);
	Customer getCustomer(String id);
	List<Customer>	getAllCustomers();
	void update(Customer customer);
}
