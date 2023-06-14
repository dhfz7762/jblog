package com.javaex.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping("/admin/write/insert")
	public String insertPost(@ModelAttribute PostVo postVo, HttpSession session) {
		int count = postService.insertPost(postVo);
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		String userId = userVo.getId();

		String result = encoding(userId);

		return "redirect:/" + result + "/admin/write";

	}
	
	@ResponseBody
	@RequestMapping("/post/click")
	public PostVo clickPost(@ModelAttribute PostVo clickPostVo) {
		PostVo postVo = postService.clickPost(clickPostVo);
		return postVo;
	}
	@ResponseBody
	@RequestMapping("/post/list")
	public List<PostVo> lastPostList(PostVo lastPostVo) {
		int count = lastPostVo.getCateNo();
		System.out.println(count);
		return postService.getPostList(count);
	}

	private String encoding(String str) {

		String result = "";
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

}
