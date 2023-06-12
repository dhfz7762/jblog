package com.javaex.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	public BlogVo getBlog(String id) {
		return blogDao.getBlog(id);
	}
	public int updateAdminBasic(BlogVo blogVo) {
		return blogDao.updateAdminBasic(blogVo);
	}
	public int updateAdminBasicNotFile(BlogVo blogVo) {
		return blogDao.updateAdminBasicNotFile(blogVo);
	}

}
