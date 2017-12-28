package com.somnath;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/* 
	 * Handler methods have flexible signatures so you can pass in obvious servlet-related objects 
	 * such as HttpServletRequest, HttpServletResponse, HttpSession and/or Principle. 
	 * Important Note: The Model may not be a parameter of any @ExceptionHandler method. Instead, 
	 * setup a model inside the method using a ModelAndView as shown by handleError() above.
	 */

	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		System.err.println("IOException occurred");
	}
	
	@ExceptionHandler({ArrayIndexOutOfBoundsException.class, StringIndexOutOfBoundsException.class})
	public String handleOutOfBoundsException(){
		return "OutOfBoundsErrorView";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleDefaultException(HttpServletRequest request, Exception exception){
		ModelAndView mView = new ModelAndView("DefaultExceptionView");
		mView.addObject("exception", exception);
		mView.addObject("url", request.getRequestURL());
		return mView;
	}
}
