package com.java.sys.rbac.controller.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mangofactory.swagger.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/sys/loginWebController")
public class SysLoginWebController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/WEB-INF/views/sys/login.jsp";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(Model model,
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
			return "/WEB-INF/views/sys/login.jsp";
		}
		return "redirect:/sys/indexWebController/index";
	}
	
}
