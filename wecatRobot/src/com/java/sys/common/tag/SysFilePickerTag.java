package com.java.sys.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.java.sys.utils.Tool;

public class SysFilePickerTag  extends TagSupport{
	private String name;
	private String value;

	/*
	    <input id="info" name="info" class="input-xlarge " type="text" readOnly="readOnly"/>
		<a href="javascript:;" onclick="clearInput('info')">清空</a>
		<input class="btn btn-success btn-small sys-img-select" type="button" value="选择" onclick="sysFileSelect('info')"/>
	 */
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		try{
			if(Tool.isBlank(value)){
				value = "";
			}
			if(Tool.isNotBlank(name)){
				out.print("<input id=\""+name+"\" name=\""+name+"\" class=\"input-xlarge \" type=\"text\" value=\""+value+"\" readOnly=\"readOnly\"/>");
				out.print("&nbsp;&nbsp;<a href=\"javascript:;\" onclick=\"clearInput('"+name+"')\">清空</a>&nbsp;");
				out.print("<input class=\"btn btn-success btn-small\" type=\"button\" value=\"选择\" onclick=\"sysFileSelect('"+name+"')\"/>");
			}else{
				out.print("");
			}
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
	
}
