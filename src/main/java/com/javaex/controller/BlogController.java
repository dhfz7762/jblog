package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/{url}")
	public String main(@PathVariable("url") String id,Model model) {
		BlogVo blogVo = blogService.getBlog(id);
        if(blogVo == null) {
        	return "error/403";
        }
        else {
        	model.addAttribute("blogUser", blogVo); 
            return "blog/blog-main"; 
        }
	}
	@GetMapping("/{url}/admin/basic")
	public String adminBasic(@PathVariable("url")String id,Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogUser", blogVo); 
		return "blog/admin/blog-admin-basic";
	}
	@RequestMapping(value="/{url}/admin/basic/modify",method= {RequestMethod.GET,RequestMethod.POST})
	public String adminBasicModify(@PathVariable("url")String id,@RequestParam("blogTitle") String blogTitle,
			@RequestParam("blogFile")MultipartFile blogFile) {
		String logoFile = blogFile.getOriginalFilename();
		if(logoFile.equals("")) {
			BlogVo blogVo = new BlogVo(id,blogTitle);
			int count = blogService.updateAdminBasicNotFile(blogVo);
		}
		else {
		BlogVo blogVo = new BlogVo(id,blogTitle,logoFile);
		int count = blogService.updateAdminBasic(blogVo);
		}
		return "main/index";
	}
	@GetMapping("/{url}/admin/category")
	public String adminCategory(@PathVariable("url")String id,Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogUser", blogVo);  
		return "blog/admin/blog-admin-cate";
	}
	@GetMapping("/{url}/admin/write")
	public String adminWrite(@PathVariable("url")String id,Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogUser", blogVo);  
		return "blog/admin/blog-admin-write";
	}
	
}
