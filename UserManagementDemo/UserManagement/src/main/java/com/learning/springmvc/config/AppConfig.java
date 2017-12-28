package com.learning.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.learning.springmvc.converter.IdToUserRoleConverter;

/* Follow below tutorial
 * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 * http://www.concretepage.com/spring-4/spring-mvc-4-security-hibernate-5-mysql-using-annotation-xml-example
 * 
 * For adding interceptor using annotation
 * http://www.concretepage.com/spring/spring-mvc/spring-handlerinterceptor-annotation-example-webmvcconfigureradapter
 * 
 * How to get ajax data in Spring MVC
 * https://www.google.co.in/search?q=how+to+get+ajax+data+in+spring+controller&cad=h
 * */

@Configuration
@EnableWebMvc
@ComponentScan("com.learning.springmvc")
public class AppConfig extends WebMvcConfigurerAdapter {
	
	// we can also configure spring mvc without extending WebMvcConfigurerAdapter.
	// see another project for more details.
	
	@Autowired
	IdToUserRoleConverter idToUserRoleConverter;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry){
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	/**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[Roles] 
     * to UserProfiles in newUser.jsp
     * 
     * Read Below links:
     * http://javabeat.net/introduction-to-spring-converters-and-formatters/
     * https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-using-type-converters-with-spring-mvc/
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(idToUserRoleConverter);
    }
    
    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
    
    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }

}
