package com.hit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hit.dao.CustomerDAO;
import com.hit.model.Customer;

@Service("customerService")
@Transactional
public class ConcreteCustomerService implements CustomerService {
	
	@Autowired
	private CustomerDAO dao;
	
	public void add(Customer customer) {
		dao.add(customer);

	}

	public void delete(String id) {
		dao.delete(id);

	}

	public Customer getCustomer(String id) {
		return dao.getCustomer(id);
	}

	public List<Customer> getAllCustomers() {
		return dao.getAllCustomers();
	}

	public void update(Customer customer) {
		dao.update(customer);
	}
}
