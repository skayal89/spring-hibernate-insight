package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.Employee;
import com.springmvc.service.EmployeeService;
import com.springmvc.util.TrieDictionary;
import com.springmvc.util.TrieNode;

@RestController
public class AppController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	TrieDictionary dictionary;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("SearchEmployee");
		return model;
	}
	
	@RequestMapping(value="/getEmployees", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<String> getEmployees(@RequestParam String employeeName){
		System.out.println("Searching "+employeeName);
		List<Employee> employees=employeeService.getEmployees();
		//dictionary=new TrieDictionary(new TrieNode());
		dictionary.buildDictionary(employees);
		return dictionary.suggest(employeeName);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return employeeService.getEmployees();
	}

}
