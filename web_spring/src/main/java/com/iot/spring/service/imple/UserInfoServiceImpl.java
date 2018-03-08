package com.iot.spring.service.imple;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.spring.controller.UserInfoController;
import com.iot.spring.dao.UserInfoDAO;
import com.iot.spring.service.UserInfoService;
import com.iot.spring.vo.UserInfoVO;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoDAO udao;
	
	@Override
	public void loginUser(HttpSession hs, UserInfoVO uiv, Map<String,Object> rMap) {
		
		List<UserInfoVO> userList = udao.selectUserInfo(uiv);		
		
		hs.setAttribute("user", userList.get(0).getUiId());	
		rMap.put("msg", "로그인에 실패하셨습니다.");
		rMap.put("biz", false);
		String userId = userList.get(0).getUiId();
		log.info("userId=>{}", userId);
		if(userList!=null) {
			rMap.put("msg", "로그인에 성공하셨습니다.");
			rMap.put("biz", true);
			rMap.put("userId", userList.get(0).getUiId());
		}
	}

	@Override
	public void insertUser(Map<String, Object> rMap, UserInfoVO uiv) {
		
		int result = udao.insertUser(uiv);
		rMap.put("msg", "회원가입에 실패하셨습니다.");
		rMap.put("biz", false);
		if(result!=0) {
			rMap.put("msg", "회원가입에 성공하셨습니다.");
			rMap.put("biz", true);
		}
		
	}

	





}
