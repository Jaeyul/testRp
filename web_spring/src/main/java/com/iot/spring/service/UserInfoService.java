package com.iot.spring.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.iot.spring.vo.UserInfoVO;

public interface UserInfoService {
	
	void loginUser(HttpSession hs, UserInfoVO uiv, Map<String,Object> rMap);
	void insertUser(Map<String,Object> rMap, UserInfoVO uiv);
	
	

}
