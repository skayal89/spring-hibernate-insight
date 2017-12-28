package com.somnath.dao;

import java.util.List;

import com.somnath.model.Employee;

public interface EmplyeeDAO {
	void add(Employee employee);
	void delete(int id);
	Employee getEmployee(int id);
	List<Employee>	getAllEmployees();
	void update(Employee employee);
}
