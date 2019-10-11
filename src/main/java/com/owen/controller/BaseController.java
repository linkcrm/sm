package com.owen.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.owen.config.Config;
import com.owen.util.Base64V2;
import com.owen.util.CookieUtil;
import com.owen.util.StringUtil;



public class BaseController {
	static final Log log = LogFactory.getLog(BaseController.class);
	
	public void addUserCookie(HttpServletResponse response,int userId,String account,int type){
		log.info(account);
		log.info(Base64V2.getBase64(account));
		CookieUtil.addCookie(response, Config.SM_USER_ID_INDEX, Base64V2.getBase64(String.valueOf(userId)), 30*24*3600);
		try {
			CookieUtil.addCookie(response, Config.SM_USER_NAME_INDEX,URLEncoder.encode(Base64V2.getBase64(account),"utf-8"), 30*24*3600);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		CookieUtil.addCookie(response, Config.SM_USER_TYPE_INDEX,Base64V2.getBase64(String.valueOf(type)), 30*24*3600);
	}
	
	public void delUserCookie(HttpServletResponse response){
		CookieUtil.addCookie(response, Config.SM_USER_ID_INDEX, "",0);
		CookieUtil.addCookie(response, Config.SM_USER_NAME_INDEX,"", 0);
		CookieUtil.addCookie(response, Config.SM_USER_TYPE_INDEX,"", 0);
	}
	
	public String getUserName(HttpServletRequest request) {
		Cookie cookie = CookieUtil.getCookieByName(request, Config.SM_USER_NAME_INDEX);
		try {
			String ss = cookie.getValue();
			String userName = Base64V2.getFromBase64(URLDecoder.decode(ss, "utf-8"));
			System.out.println(userName);
			return userName;
		}catch(Exception e) {
			return "";
		}
	}
	
	public int getAdminType(HttpServletRequest request) {
		try {
			Cookie cookie = CookieUtil.getCookieByName(request,
					Config.SM_USER_TYPE_INDEX);
			String str = cookie.getValue();
			if (StringUtil.isEmpty(str)) {
				return -1;
			}
			return Integer.valueOf(Base64V2.getFromBase64(str));
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	public int getUserId(HttpServletRequest request) {
		try {
			Cookie cookie = CookieUtil.getCookieByName(request,
					Config.SM_USER_ID_INDEX);
			String userIdStr = cookie.getValue();
			if (StringUtil.isEmpty(userIdStr)) {
				return -1;
			}
			return Integer.valueOf(Base64V2.getFromBase64(userIdStr));
		} catch (Exception e) {
			return -1;
		}
	}

	public boolean isLogined(HttpServletRequest request){
		if(getUserId(request) <=0 ){
			return false;
		}
		return true;
	}
}
