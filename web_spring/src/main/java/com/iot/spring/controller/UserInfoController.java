package com.iot.spring.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iot.spring.service.UserInfoService;
import com.iot.spring.vo.UserInfoVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {	
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	UserInfoService us;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> login(@RequestBody UserInfoVO uiv, HttpSession hs) {
		Map<String,Object> rMap = new LinkedHashMap<String, Object>();
		log.info("UserInfoVo =>{}", uiv);
		us.loginUser(hs, uiv, rMap);
		return rMap;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> signup(@RequestBody UserInfoVO uiv) {
		log.info("UserInfoVo =>{}", uiv);
		Map<String,Object> rMap = new LinkedHashMap<String, Object>();
		us.insertUser(rMap, uiv);
		
		return rMap;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> test(HttpSession hs) {
		Map<String,String> rMap = new LinkedHashMap<String, String>();
		String userId = (String) hs.getAttribute("userId");
		log.info("userId=>{}",userId);
		rMap.put("userId", userId);
		return rMap;
	}

	
	
}
