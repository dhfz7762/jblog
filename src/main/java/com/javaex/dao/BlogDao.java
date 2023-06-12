package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo getBlog(String id) {
		return sqlSession.selectOne("blog.getBlog", id);
	}
	public int updateAdminBasic(BlogVo blogVo) {
		return sqlSession.update("blog.updateAdminBasic",blogVo);
	}
	public int updateAdminBasicNotFile(BlogVo blogVo) {
		return sqlSession.update("blog.updateAdminBasicNotFile",blogVo);
	}

}
