package com.somnath.service;

import java.util.List;

import com.somnath.model.Employee;

public interface EmplyeeService {
	void add(Employee employee);
	void delete(int id);
	Employee getEmplyee(int id);
	List<Employee>	getAllEmployees();
	void update(Employee employee);
}
