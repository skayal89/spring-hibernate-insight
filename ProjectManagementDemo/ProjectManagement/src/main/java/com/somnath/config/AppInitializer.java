package com.somnath.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext=new AnnotationConfigWebApplicationContext();
		applicationContext.register(AppConfig.class);
		applicationContext.setServletContext(container);
		
		ServletRegistration.Dynamic servlet=container.addServlet("servlet-dispatcher", new DispatcherServlet(applicationContext));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
