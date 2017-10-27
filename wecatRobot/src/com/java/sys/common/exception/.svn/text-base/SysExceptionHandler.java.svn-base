package com.java.sys.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Component
public class SysExceptionHandler extends SimpleMappingExceptionResolver {
	private static Logger log = Logger.getLogger(SysExceptionHandler.class);
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e) {
		response.setCharacterEncoding("UTF-8");
		if(e instanceof org.apache.shiro.authz.UnauthorizedException){
			return new ModelAndView("/WEB-INF/views/shiro/unauth.jsp");
		}else if(e instanceof org.springframework.web.multipart.MultipartException){
			return new ModelAndView("/WEB-INF/views/sys/noFileReq.jsp");
		}else if(e instanceof java.io.IOException){
			System.out.println("--- : java.io.IOException");
			System.out.println(e.getMessage());
		}else{
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw,true));
			log.error(sw.toString());
		}
		return null;
	}
	
}
