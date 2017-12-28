package com.somnath.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somnath.dao.EmplyeeDAO;
import com.somnath.model.Employee;

@Service
@Transactional
public class ConcreteEmplyeeService implements EmplyeeService {

	@Autowired
	private EmplyeeDAO dao;
	
	public void add(Employee employee) {
		dao.add(employee);

	}

	public void delete(int id) {
		dao.delete(id);

	}

	public Employee getEmplyee(int id) {
		return dao.getEmployee(id);
	}

	public List<Employee> getAllEmployees() {
		return dao.getAllEmployees();
	}

	public void update(Employee employee) {
		dao.update(employee);

	}

}
