package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@RequestMapping("/admin/write/insert")
	public String insertPost(@ModelAttribute PostVo postVo) {
		int count = postService.insertPost(postVo);
		
		return "redirect:/";
		
	}
	
}

