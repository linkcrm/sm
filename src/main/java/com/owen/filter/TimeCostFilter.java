package com.owen.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCostFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	String url = request.getScheme()+"://"+request.getServerName()+":"
				+request.getServerPort()+request.getContextPath()+request.getServletPath()+"?"+request.getQueryString();
    	logger.info("Request url:{}",url);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    	Date startDate = new Date();
        filterChain.doFilter(request,servletResponse);
        Date endDate = new Date();
        logger.info("Request url:{},开始时间:{},Execute time cost:{}",url,format.format(startDate),(endDate.getTime()-startDate.getTime()));
    }
 
    @Override
    public void destroy() {
 
    }
}