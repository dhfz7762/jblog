package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertPost(PostVo postVo) {
		return sqlSession.insert("post.insertPost", postVo);
	}
	public PostVo getLastPost(String id) {
		return sqlSession.selectOne("post.getLastPost", id);
	}
	public List<PostVo> getPostList(String cateNo){
		return sqlSession.selectList("post.getPostList", cateNo);
	}

}
