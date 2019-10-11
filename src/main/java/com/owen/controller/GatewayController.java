package com.owen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.owen.bazi.ResultMap;
import com.owen.bean.ClientRequest;
import com.owen.bean.User;
import com.owen.service.UserService;
import com.owen.util.PoiWordUtils;
import com.owen.util.StringUtil;

@RestController
public class GatewayController extends BaseController {
	@Autowired
	UserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		return loginGet(request);
	}
	
	
	@RequestMapping(value = "/user_add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUser(HttpServletRequest request, ClientRequest req,String userName,String pw) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		if (!isLogined(request) || getAdminType(request) <= 0) {
			logger.info("没有权限");
			map.put("code", "-1");
			map.put("errMsg", "没有权限");
			return map;
		}
		if( userName ==  null || "".equals(userName.trim()) || pw == null || "".equals(pw.trim())) {
			logger.info("输入非法");
			map.put("code", "-2");
			map.put("errMsg", "输入非法");
			return map;
		}
		
		logger.info("user name:"+userName+";password:"+pw);
		
		User user = new User();
		user.setFUserName(userName);
		user.setFPassword(pw);
		
		User userInDb = userService.queryUserByAccount(user.getFUserName());

		if (userInDb != null) {
			
			logger.info("用户名已经存在");
			map.put("code", "-3");
			map.put("errMsg", "用户名已经存在");
			return map;
		}

		
		
		int res = userService.add(user);
		
		map.put("code", 0);
		map.put("result", res);

		return map;
	}
	
	
	@RequestMapping(value = "/user_changePw", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changeUserPw(HttpServletRequest request, ClientRequest req,Integer userId,String pw) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		if (!isLogined(request) || getAdminType(request) <= 0) {
			logger.info("没有权限");
			map.put("code", "-1");
			map.put("errMsg", "没有权限");
			return map;
		}
		if( userId ==  null || pw == null || "".equals(pw.trim())) {
			logger.info("输入非法");
			map.put("code", "-2");
			map.put("errMsg", "输入非法");
			return map;
		}
		
		logger.info("user id:"+userId+";newpw:"+pw);

		int res = userService.updatePw(userId, pw);
		
		map.put("code", 0);
		map.put("result", res);

		return map;
	}
	
	
	@RequestMapping(value = "/user_del", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delUser(HttpServletRequest request, ClientRequest req,Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		if (!isLogined(request) || getAdminType(request) <= 0) {
			logger.info("没有权限");
			map.put("code", "-1");
			map.put("errMsg", "没有权限");
			return map;
		}
		if( userId == null) {
			logger.info("输入信息不全");
			map.put("code", "-2");
			map.put("errMsg", "输入信息不全");
			return map;
		}
		
		logger.info("user id:"+userId);

		int res = userService.deleteById(userId);
		
		map.put("code", 0);
		map.put("result", res);

		return map;
	}
	
	@RequestMapping(value="/user_admin",method=RequestMethod.GET)
	public ModelAndView userAdmin(HttpServletRequest request,HttpServletResponse response,String querySubmitter) {
		if( !isLogined(request) ) {
			ModelAndView mv = new ModelAndView("login");
			return mv;
		}
		int userId = getUserId(request);
		logger.info("userId:"+userId);
		User user = userService.queryLearnResouceById(userId);
		if( user.getFIsAdmin() <= 0 ) {
			ModelAndView mv = new ModelAndView("no_permit");
			return mv;
		}
		ModelAndView mv = new ModelAndView("user_admin");
		List<User> users = userService.queryAllUsers(querySubmitter);
		mv.addObject("users", users);
		mv.addObject("isAdmin",1);
		mv.addObject("querySubmitter",querySubmitter);
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request) {
		if(isLogined(request)) {
			logger.info("登录成功,用户id:{}",getUserId(request));
			ModelAndView mv = new ModelAndView("login_success");
			mv.addObject("userName",getUserName(request));
			mv.addObject("isAdmin",getAdminType(request));
			return mv;
		}
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	@RequestMapping(value="/log_out",method=RequestMethod.GET)
	public ModelAndView logOut(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("redirect:/login");
		delUserCookie(response);
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(User user, HttpServletResponse response) {

		logger.info(String.format("user name:%s,password:%s", user.getFUserName(), user.getFPassword()));

		ModelAndView mv = new ModelAndView("login");

		mv.addObject("userName", user.getFUserName());
		mv.addObject("password", user.getFPassword());

		if (user == null || StringUtil.isEmpty(user.getFPassword()) || StringUtil.isEmpty(user.getFUserName())) {
			mv.addObject("errMsg", "信息未填写完整.");

			return mv;
		}

		User userInDb = userService.queryUserByAccount(user.getFUserName());

		if (userInDb == null) {

			mv.addObject("errMsg", "账号不对.");

			return mv;
		}
		if (!userInDb.getFPassword().equals(user.getFPassword())) {
			logger.info(
					String.format("DB:user name:%s,password:%s", userInDb.getFUserName(), userInDb.getFPassword()));

			mv.addObject("errMsg", "密码不对.");

			return mv;
		}
		System.out.println(userInDb);

		addUserCookie(response, userInDb.getFUserId(), userInDb.getFUserName(), userInDb.getFIsAdmin());

		mv = new ModelAndView("fill");

		return mv;
	}

	@RequestMapping(value = "/fill", method = RequestMethod.GET)
	public ModelAndView fillGet(HttpServletRequest request) {
		if (!isLogined(request)) {
			logger.info("未登录");
			ModelAndView mv = new ModelAndView("login");
			return mv;
		}
		ModelAndView mv = new ModelAndView("fill");
		mv.addObject("isAdmin",getAdminType(request));
		return mv;
	}

	@RequestMapping(value = "/analysis", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> analysis(HttpServletRequest request, ClientRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (!isLogined(request)) {
			logger.info("未登录");
			map.put("code", "-1");
			map.put("errMsg", "未登录");
			return map;
		}
		logger.info(req.toString());

		logger.info("用户：{} 算{}一生批注",getUserName(request),req.getName());
		ResultMap resultMap = new ResultMap(req, true);

		map.put("code", 0);
		map.put("data", resultMap.analysis());

		return map;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response, ClientRequest req) {
		if (!isLogined(request)) {
			logger.info("未登录");
			return;
		}
		logger.info("用户：{} 下载{}一生批注",getUserName(request),req.getName());
		ResultMap resultMap = new ResultMap(req, false);
		try {
			PoiWordUtils.download(response, resultMap.analysis(),  req.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
