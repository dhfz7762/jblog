package com.javaex.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public int insertPost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}
	public PostVo getLastPost(String id) {
		return postDao.getLastPost(id);
	}
	public List<PostVo> getPostList(int cateNo){
		return postDao.getPostList(cateNo);
	}
	public PostVo clickPost(PostVo postVo) {
		return postDao.clickPost(postVo);
	}
	

}
