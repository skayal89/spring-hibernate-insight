package com.somnath.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.somnath.model.Employee;
import com.somnath.service.EmplyeeService;

@Controller
@RequestMapping("/")
@ComponentScan("com.somnath")
public class AppController {

	@Autowired
	EmplyeeService emplyeeService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value={"/","/list"}, method=RequestMethod.GET)
	public ModelAndView listEmplyees()
	{
		List<Employee> list=emplyeeService.getAllEmployees();
		System.out.println(list);
		ModelAndView modelAndView=new ModelAndView("EmployeeList");
		modelAndView.addObject("employeeList", list);
		return modelAndView;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView addEmployee(){
		Employee employee=new Employee();
		ModelAndView mView=new ModelAndView("Registration");
		mView.addObject("edit", false);
		mView.addObject("employee2", employee); // need to add student object if we use 'path' 
		// which binds to a variable and the object received by 'commandName' or 'modelAttribute' tag
		return mView;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String saveEmplyee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result){
		System.out.println("saveEmplyee executing...");
		if(result.hasErrors())
		{
			System.out.println("saveEmplyee/hasErrors");
			//return addEmployee().getViewName();
		}
		
		emplyeeService.add(employee);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/edit/{empid}", method=RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable("empid") int empid){
		Employee employee=emplyeeService.getEmplyee(empid);
		ModelAndView mView=new ModelAndView("Registration");
		mView.addObject("edit", true);
		mView.addObject("employee2", employee); // need to add student object if we use 'path' 
		// which binds to a variable and the object received by 'commandName' or 'modelAttribute' tag
		return mView;
	}
	
	@RequestMapping(value="/edit/{empid}", method=RequestMethod.POST)
	public String updateEmplyee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result){
		if(result.hasErrors())
		{
			return editEmployee(employee.getId()).getViewName();
		}
		
		emplyeeService.update(employee);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete/{empid}", method=RequestMethod.GET)
	public String deleteEmplyee(@PathVariable("empid") int empid){
		emplyeeService.delete(empid);
		return "redirect:/list";
	}
}
