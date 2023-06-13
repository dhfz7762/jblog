package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CateDao;
import com.javaex.vo.CateVo;

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
	public String getLastCate(String id) {
		return cateDao.getLastCate(id);
	}

}
