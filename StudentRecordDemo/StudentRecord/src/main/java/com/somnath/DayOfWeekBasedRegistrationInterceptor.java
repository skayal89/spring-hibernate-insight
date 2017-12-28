package com.somnath;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DayOfWeekBasedRegistrationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
	{
		long startTime = System.currentTimeMillis();
		System.out.println("[RegistrationInterceptor/preHandle] Request URL::" + request.getRequestURL().toString()
                + " Sent to Handler :: Current Time=" + startTime);
		request.setAttribute("startTime", startTime);
		Calendar calendar=Calendar.getInstance();
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY){
			response.getWriter().write("Oops! We are closed on Sunday.");
			return false;
		}
		return true;
	}
	
	 @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	        System.out.println("[RegistrationInterceptor/postHandle] Request URL::" + request.getRequestURL().toString()
	                + " Sent to Handler :: Current Time=" + System.currentTimeMillis());
	        //we can add attributes in the modelAndView and use that in the view page
	        //modelAndView.setViewName("WeekBasedAccessError");
	        System.out.println("[RegistrationInterceptor/postHandle] viewName:"+modelAndView.getViewName());
	    }
	 
	 @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	        long startTime = (Long) request.getAttribute("startTime");
	        System.out.println("[RegistrationInterceptor/afterComplete] Request URL::" + request.getRequestURL().toString()
	                + ":: End Time=" + System.currentTimeMillis());
	        System.out.println("[RegistrationInterceptor/afterComplete] Request URL::" + request.getRequestURL().toString()
	                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	    }
}
