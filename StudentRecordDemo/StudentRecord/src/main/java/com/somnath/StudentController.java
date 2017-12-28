package com.somnath;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	@RequestMapping("/home")
	public ModelAndView displayStudentList()
	{
		ModelAndView mView=new ModelAndView("StudentList");
		mView.addObject("studentList", "Somnath Kayal");
		return mView;
	}
}
