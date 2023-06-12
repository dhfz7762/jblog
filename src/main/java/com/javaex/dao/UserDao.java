package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int joinUser(UserVo userVo) {
		return sqlSession.insert("user.joinUser", userVo);
	}
	public UserVo loginUser(UserVo userVo) {
		return sqlSession.selectOne("user.loginUser", userVo);
	}
	public int idCheck(String id) {
		return sqlSession.selectOne("user.idCheck", id);
	}
	public UserVo getUser(String id) {
		return sqlSession.selectOne("user.getUser",id);
	}
	public int makeBlog(UserVo userVo) {
		return sqlSession.insert("user.makeBlog", userVo);
	}

}
