package com.somnath;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DayOfWeekBasedAccessInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
	{
		int flag=1;
		if(flag==1) throw new NullPointerException("NullPointerException thrown");
		long startTime = System.currentTimeMillis();
		System.out.println("[preHandle] Request URL::" + request.getRequestURL().toString()
                + " Sent to Handler :: Current Time=" + startTime);
		request.setAttribute("startTime", startTime);
		Calendar calendar=Calendar.getInstance();
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			response.getWriter().write("Oops! We are closed on Sunday.");
			return false;
		}
		return true;
	}
	
	 @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	        System.out.println("[postHandle] Request URL::" + request.getRequestURL().toString()
	                + " Sent to Handler :: Current Time=" + System.currentTimeMillis());
	        //we can add attributes in the modelAndView and use that in the view page
	        //modelAndView.setViewName("WeekBasedAccessError");
	        System.out.println("[postHandle] viewName:"+modelAndView.getViewName());
	    }
	 
	 @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	        long startTime = (Long) request.getAttribute("startTime");
	        System.out.println("[afterComplete] Request URL::" + request.getRequestURL().toString()
	                + ":: End Time=" + System.currentTimeMillis());
	        System.out.println("[afterComplete] Request URL::" + request.getRequestURL().toString()
	                + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
	    }
}
