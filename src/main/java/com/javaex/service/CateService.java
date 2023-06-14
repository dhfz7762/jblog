package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.dao.CateDao;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Service
public class CateService {
	
	@Autowired
	private CateDao cateDao;
	
	public CateVo insertCate(CateVo cateVo){
		 cateDao.insertCate(cateVo);
		 int cateNo = cateVo.getCateNo();
		 return cateDao.getCate(cateNo);
	}
	public List<CateVo> getCateList(String id){
		return cateDao.getCateList(id);
	}
	public int getLastCate(String id) {
		return cateDao.getLastCate(id);
	}
	public int deleteCate(CateVo cateVo) {
		if(cateVo.getPostCount()>0) {
			return 0;
		}
		else {
			return cateDao.deleteCate(cateVo);
		}	
	}
	public List<PostVo> getPostList(int cateNo){
		return cateDao.getPostList(cateNo);
	}

}
