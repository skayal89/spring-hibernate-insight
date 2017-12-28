package com.hit.controller;

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

import com.hit.model.Account;
import com.hit.model.Category;
import com.hit.model.Customer;
import com.hit.model.Product;
import com.hit.service.CustomerService;
import com.hit.service.ProductService;

@Controller
@RequestMapping("/customer/")
@ComponentScan("com.hit")
public class AppControllerCustomer {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value={"/list"}, method=RequestMethod.GET)
	public ModelAndView listCustomers()
	{
		List<Customer> list=customerService.getAllCustomers();
		System.out.println(list);
		ModelAndView modelAndView=new ModelAndView("CustomerList");
		modelAndView.addObject("customerList", list);
		return modelAndView;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView addCustomer(){
		Customer customer=new Customer();
		Account account=new Account();
		customer.setAccount(account);
		ModelAndView mView=new ModelAndView("Registration");
		mView.addObject("edit", false);
		mView.addObject("customer2", customer); 
		// need to add student object if we use 'path' 
		// which binds to a variable and the object received 
		// by 'commandName' or 'modelAttribute' tag
		return mView;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result){
		System.out.println("saveCustomer executing...");
		if(result.hasErrors())
		{
			System.out.println("saveCustomer/hasErrors");
			//return addEmployee().getViewName();
		}
		
		customerService.add(customer);
		return "redirect:/customer/list";
	}
	
	@RequestMapping(value="/edit/{custid}", method=RequestMethod.GET)
	public ModelAndView editCustomer(@PathVariable("custid") String custid){
		Customer customer=customerService.getCustomer(custid);
		ModelAndView mView=new ModelAndView("Registration");
		mView.addObject("edit", true);
		mView.addObject("customer2", customer); // need to add student object if we use 'path' 
		// which binds to a variable and the object received by 'commandName' or 'modelAttribute' tag
		return mView;
	}
	
	@RequestMapping(value="/edit/{custid}", method=RequestMethod.POST)
	public String updateCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result){
		if(result.hasErrors())
		{
			return editCustomer(customer.getUserId()).getViewName();
		}
		
		customerService.update(customer);
		return "redirect:/customer/list";
	}
	
	@RequestMapping(value="/delete/{custid}", method=RequestMethod.GET)
	public String deleteCustomer(@PathVariable("custid") String custid){
		customerService.delete(custid);
		return "redirect:/customer/list";
	}
	
	
}
