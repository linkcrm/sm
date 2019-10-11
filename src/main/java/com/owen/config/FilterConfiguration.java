package com.owen.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.owen.filter.TimeCostFilter;
import com.owen.filter.AuthFilter;

@Configuration
public class FilterConfiguration {
	
	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new TimeCostFilter());
		registration.addUrlPatterns("/*"); //
		registration.addInitParameter("paramName", "paramValue"); //
		registration.setName("timeCostFilter");
		registration.setOrder(1);
		return registration;
	}
 
	@Bean
	public FilterRegistrationBean authFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new AuthFilter());
		registration.addUrlPatterns("/*"); //
		registration.addInitParameter("paramName", "paramValue"); //
		registration.setName("authFilter");
		registration.setOrder(2);
		return registration;
	}
}
