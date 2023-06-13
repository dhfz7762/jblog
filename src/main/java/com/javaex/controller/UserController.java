package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/loginForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String loginForm() {
		return "user/loginForm";
	}
	@RequestMapping(value="/login",method= {RequestMethod.GET,RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo,HttpSession session) {
		UserVo authUser = userService.loginUser(userVo);
		
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			return "main/index";
		}else {
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	@RequestMapping(value="/joinForm",method= {RequestMethod.GET,RequestMethod.POST})
	public String joinForm() {
		return "user/joinForm";
	}
	@RequestMapping(value="/join",method= {RequestMethod.GET,RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		int count = userService.joinUser(userVo);
		int count2 = userService.makeBlog(userVo);
		int count3 = userService.makeCate(userVo);
		if(count+count2+count3==3) {
			return "user/joinSuccess";
		}
		else {
			return "user/joinForm";
		}
		
	}
	@ResponseBody
	@RequestMapping(value="/idcheck",method = {RequestMethod.GET,RequestMethod.POST})
	public int idCheck(@ModelAttribute("id")String id) {
		int check = userService.idCheck(id);
		return check;
	}
	@RequestMapping(value="/logout",method= {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		session.removeAttribute("authUser"); //session 메모리 삭제
		session.invalidate(); 
		
		return "redirect:/";
	}

}
