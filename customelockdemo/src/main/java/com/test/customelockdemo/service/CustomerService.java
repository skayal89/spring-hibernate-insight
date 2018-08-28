package com.test.customelockdemo.service;

import com.test.customelockdemo.model.Customer;

import java.util.List;

public interface CustomerService {
    void add(Customer customer);
    List<Customer> findAll();
    Customer findById(int id);
}
