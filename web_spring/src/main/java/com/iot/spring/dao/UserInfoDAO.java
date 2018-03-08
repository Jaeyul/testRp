package com.iot.spring.dao;

import java.util.List;

import com.iot.spring.vo.UserInfoVO;

public interface UserInfoDAO {
	
	List<UserInfoVO> selectUserInfo(UserInfoVO uiv);
	int insertUser(UserInfoVO uiv);

}
