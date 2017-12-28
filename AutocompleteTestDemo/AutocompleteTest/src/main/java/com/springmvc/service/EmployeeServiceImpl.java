package com.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	List<Employee> employees;
	
	public EmployeeServiceImpl() {
		employees=new ArrayList<Employee>();
		
		Employee e1=new Employee();
		e1.setId(101);
		e1.setName("Somnath");
		e1.setSalary(20000);
		
		Employee e2=new Employee();
		e2.setId(102);
		e2.setName("Sumit");
		e2.setSalary(30000);
		
		Employee e3=new Employee();
		e3.setId(103);
		e3.setName("Arnab");
		e3.setSalary(25000);
		
		Employee e4=new Employee();
		e4.setId(101);
		e4.setName("Ashish");
		e4.setSalary(35000);
		
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	
	public List<Employee> search(String empName){
		List<Employee> empList=new ArrayList<Employee>();
		for(Employee emp : employees){
			if(emp.getName().equalsIgnoreCase(empName)){
				empList.add(emp);
			}
		}
		return empList;
	}

}
