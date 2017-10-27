package com.java.sys.rbac.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mangofactory.swagger.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/sys/indexWebController")
public class SysIndexWebController {
	
	@RequestMapping("/index")
	public String index(){
		return "/WEB-INF/views/sys/index.jsp";
	}
}
