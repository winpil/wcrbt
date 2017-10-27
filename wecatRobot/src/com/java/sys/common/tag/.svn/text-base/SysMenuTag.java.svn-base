package com.java.sys.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.java.sys.cache.RedisUtils;
import com.java.sys.rbac.entity.SysMenu;
import com.java.sys.rbac.service.SysMenuService;
import com.java.sys.utils.SpringContextHolder;
import com.java.sys.utils.Tool;

public class SysMenuTag extends TagSupport{
	
	private String name;
	private String value;
	private String required;

	public int doStartTag() throws JspException {
		if(Tool.isNotBlank(required) && "true".equals(required)){
			required = "required";
		}else{
			required = "";
		}
		
		JspWriter out = this.pageContext.getOut();
		try{
			
			if(Tool.isBlank(name)){
				out.println("");
				return SKIP_BODY;
			}
			String id = "";
			String display = "";
			if(Tool.isNotBlank(value)){
				SysMenuService sysMenuService = SpringContextHolder.getBean(SysMenuService.class);
				SysMenu sysMenu = sysMenuService.get(value);
				if(sysMenu != null){
					id = sysMenu.getId();
					display = sysMenu.getName();
				}
			}

			out.println("<div class='input-append'>");
			
			out.println("<input id='"+name+"' name='"+name+"' type='hidden' value='"+id+"'/>");
			out.println("<input title='"+name+"' class='span3 select-sys-menu-display "+required+"' type='text' readOnly='true' value='"+display+"'>");
			out.println("<a id='select-sys-menu' href='#sysMenuModel' class='btn' data-toggle='modal'>&nbsp;&nbsp;&nbsp;<i class='icon-search'></i>&nbsp;&nbsp;&nbsp;</a>");
			out.println("<div id='sysMenuModel' class='modal hide fade sys-menu-model' tabindex='-1'>");
			out.println("<div class='modal-header'>");
			out.println("<button type='button' class='close' data-dismiss='modal'>×</button>");
			out.println("<h4>选择菜单</h4>");
			out.println("</div>");
			out.println("<div class='modal-body'>");
			out.println("<ul id='sys-menu-tree' class='ztree'></ul>");
			out.println("</div>");
			out.println("</div>");
			
			out.println("</div>");
		}catch(Exception e){
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}


	
}
