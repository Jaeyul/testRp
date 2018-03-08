package com.iot.spring.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iot.spring.dao.UserInfoDAO;
import com.iot.spring.vo.UserInfoVO;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {
	
	@Autowired
	SqlSessionFactory ssf;
	
	@Override
	public List<UserInfoVO> selectUserInfo(UserInfoVO uiv) {
		SqlSession ss = ssf.openSession();
		List<UserInfoVO> userList = ss.selectList("user_info.selectUserInfo", uiv);
		System.out.println(userList);
		return userList;
	}

	@Override
	public int insertUser(UserInfoVO uiv) {
		int result = 0;
		SqlSession ss = ssf.openSession();
		result = ss.insert("user_info.insertUserInfo", uiv);
		return result;
	}

	

}
