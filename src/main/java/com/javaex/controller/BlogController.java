package com.javaex.controller;

import java.util.List;

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
import com.javaex.service.CateService;
import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CateService cateService;
	@Autowired
	private PostService postService;
	
	@GetMapping("/{url}")
	public String main(@PathVariable("url") String id,Model model,@RequestParam(value="cateNo", required = false, defaultValue="d") String cateNo) {
		BlogVo blogVo = blogService.getBlog(id);
		List<CateVo> cateList = cateService.getCateList(id);
		PostVo postVo = postService.getLastPost(id);
		if(cateNo.equals("d")) {
			cateNo = cateService.getLastCate(id);
			System.out.println(cateNo);
		}
		List<PostVo> postList = postService.getPostList(cateNo);
        if(blogVo == null) {
        	return "error/403";
        }
        else if(cateList == null) {
        	return "error/403";
        }
        else {
            model.addAttribute("lastPost",postVo);
        	model.addAttribute("blogUser", blogVo); 
        	model.addAttribute("cateList",cateList);
        	model.addAttribute("postList",postList);
            return "blog/blog-main"; 
        }
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
	@GetMapping("/{url}/admin/{url2}")
	public String adminBasic(@PathVariable("url")String id,@PathVariable("url2")String link,Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogUser", blogVo);
		List<CateVo> cateList = cateService.getCateList(id);
	    model.addAttribute("cateList",cateList);
	    
		if(link.equals("basic")) {

			return "blog/admin/blog-admin-basic";
		}
		else if(link.equals("category")) {
			//여기다가 그.. 포스트있으면 카운트해보는걸로 널때문에 안돼면 화남
			return "blog/admin/blog-admin-cate";
		}
		else if(link.equals("write")) {
			  
			return "blog/admin/blog-admin-write";
		}
		else {
			return "error/403";
		}
		
	}
	
}
