package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;


@Repository
public class CateDao {
	
	@Autowired
	private SqlSession sqlSession;
	public List<CateVo> getCateList(String id){
		System.out.println(id);
		return sqlSession.selectList("cate.getCateList", id);
	}
	
	public int insertCate(CateVo cateVo) {
		sqlSession.insert("cate.insertCate", cateVo);
		return cateVo.getCateNo();
	}
	
	public CateVo getCate(int cateNo) {
		return sqlSession.selectOne("cate.getCate", cateNo);
	}
	public String getLastCate(String id) {
		 CateVo cateVo = sqlSession.selectOne("cate.getLastCate", id);
		 return String.valueOf(cateVo.getCateNo());
	}

}
