package com.somnath;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		/*webDataBinder.setDisallowedFields(new String[]{"contact"});
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd**mm**yyyy");
		webDataBinder.registerCustomEditor(Date.class, "dateOfBirth", new CustomDateEditor(dateFormat, false));*/
		webDataBinder.registerCustomEditor(String.class, "name", new StudentNameEditor());
	}
	
	@RequestMapping(value="/doRegister", method=RequestMethod.POST)
	public ModelAndView doRegister(@Valid @ModelAttribute("student1") Student student, BindingResult result)
	{
		if(result.hasErrors())
		{
			return register();
		}
		ModelAndView model=new ModelAndView("StudentList");
		model.addObject("msg", "Registration Successful");
		return model;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register()
	{
		ArrayList<String> genderList=new ArrayList<>();
		genderList.add("Male");
		genderList.add("Female");
		
		ArrayList<String> frameworks=new ArrayList<>();
		frameworks.add("Spring MVC");
		frameworks.add("Struts");
		frameworks.add("Maven");
		frameworks.add("Hibernate");
		
		ModelAndView model=new ModelAndView("RegistrationForm");
		model.addObject("genderList", genderList);
		model.addObject("frameworkList", frameworks);
		model.addObject("student2", new Student()); // need to add student object if we use 'path' 
		// which binds to a variable and the object received by 'commandName' or 'modelAttribute' tag
		return model;
	}
}
