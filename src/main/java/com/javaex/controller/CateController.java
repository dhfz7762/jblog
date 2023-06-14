package com.javaex.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CateService;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class CateController {
	
	@Autowired
	private CateService cateService;
	
	@ResponseBody
	@RequestMapping("/admin/category/insert")
	public CateVo insertCate(@ModelAttribute CateVo cateVo){
		return cateService.insertCate(cateVo);
	}
	@ResponseBody
	@RequestMapping("/admin/category/delete")
	public int deleteCate(@ModelAttribute CateVo cateVo){
		return cateService.deleteCate(cateVo);
	}
	@ResponseBody
	@RequestMapping("/category/list")
	public List<CateVo> cateList(UserVo userVo){
		return cateService.getCateList(userVo.getId());
	}
	@ResponseBody
	@RequestMapping("/category/click")
	public List<PostVo> postList(CateVo cateVo){
		return cateService.getPostList(cateVo.getCateNo());
	}

}
