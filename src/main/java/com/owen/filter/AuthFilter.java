package com.owen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AuthFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	logger.info("A auth filter");
    	filterChain.doFilter(servletRequest,servletResponse);
    	logger.info("A auth filter end!");
    }
 
    @Override
    public void destroy() {
 
    }
}