package com.java.sys.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.java.sys.utils.Tool;

public class SysImgPickerTag extends TagSupport{
	private String name;
	private String value;
	private String multiSelect;
	
	/*
	  	<input id="avatar" type="hidden" name="avatar"/>
		<div class="div-sys-img-select-append" style="position:relative;width:100px;height:100px;display:inline-block;">
			<img class="img-sys-img-select-append" src="/java/uploads/sys/images/36ff37ac5d5b473db2d9960e7cc780201472441738912.jpg" style="width:100%;height:100%;"/>
			<a title="avatar" class="a-sys-img-select-append" href="javascript:;" style="position:absolute;top:0;right:0;">×</a>
		</div>
		<input class="btn btn-success btn-small sys-img-select" type="button" value="选择" onclick="sysImgSelect('avatar','true')"/>
	 */
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		try{
			if(Tool.isNotBlank(name)){
				if(Tool.isBlank(value)){
					value = "";
				}
				if(!(Tool.isNotBlank(multiSelect) && "true".equals(multiSelect))){
					multiSelect = "false";
				}
				out.println("<input id='"+name+"' type='hidden' name='"+name+"' value='"+value+"'/>");
				if(Tool.isNotBlank(value)){
					String[] imgs = value.split(",");
					if(imgs != null && imgs.length>0){
						for(String img : imgs){
							out.println("<div class='div-sys-img-select-append' style='position:relative;width:100px;height:100px;display:inline-block;'>");
							out.println("<img class='img-sys-img-select-append' src='"+img+"' style='width:100%;height:100%;'/>");
							out.println("<a title='"+name+"' class='a-sys-img-select-append' href='javascript:;' style='position:absolute;top:0;right:0;'>×</a>");
							out.println("</div>");
						}
					}
				}
				out.println("<input class=\"btn btn-success btn-small sys-img-select\" type=\"button\" value=\"选择\" onclick=\"sysImgSelect('"+name+"','"+multiSelect+"')\"/>");
			}else{
				out.println("");
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

	public String getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}

	
}
