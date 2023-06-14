package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	public String restore(MultipartFile file) {
		String saveDir = "C:\\javaStudy\\upload";
	    String orgName = file.getOriginalFilename();
	    String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	    String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
	    String filePath = saveDir + "\\" + saveName;
	    long fileSize = file.getSize();
	    try {
		byte[] fileData = file.getBytes();
	    OutputStream out = new FileOutputStream(filePath);
	    BufferedOutputStream bout = new BufferedOutputStream(out);
	    bout.write(fileData);
	    bout.close();
	    }
	    catch(IOException e) {
	    	e.printStackTrace();
	    }
	    return saveName;
	}

}
