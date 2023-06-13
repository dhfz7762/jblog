package com.javaex.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CateService;
import com.javaex.vo.CateVo;

@Controller
public class CateController {
	
	@Autowired
	private CateService cateService;
	
	@ResponseBody
	@RequestMapping("/admin/category/insert")
	public CateVo insertCate(@ModelAttribute CateVo cateVo){
		System.out.println(cateVo);
		CateVo categoryVo = cateService.insertCate(cateVo);
		return categoryVo;
	}

}
