package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;


@Repository
public class CateDao {
	
	@Autowired
	private SqlSession sqlSession;
	public List<CateVo> getCateList(String id){
		return sqlSession.selectList("cate.getCateList", id);
	}
	
	public int insertCate(CateVo cateVo) {
		sqlSession.insert("cate.insertCate", cateVo);
		return cateVo.getCateNo();
	}
	
	public CateVo getCate(int cateNo) {
		return sqlSession.selectOne("cate.getCate", cateNo);
	}
	public int getLastCate(String id) {
		CateVo cateVo = sqlSession.selectOne("cate.getLastCate",id);
		return cateVo.getCateNo();
	}
	public int deleteCate(CateVo cateVo) {
		return sqlSession.delete("cate.deleteCate", cateVo);
	}
	public List<PostVo> getPostList(int cateNo){
		return sqlSession.selectList("cate.postList",cateNo);
	}

}
