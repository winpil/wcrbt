package com.java.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mangofactory.swagger.annotations.ApiIgnore;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.sys.basic.controller.BaseController;
import com.java.sys.common.SysPage;
import com.java.entity.SyncFieldMapped;
import com.java.service.SyncFieldMappedService;
import com.java.sys.utils.Tool;

@ApiIgnore
@Controller
@RequestMapping("/sys/syncFieldMappedWebController")
public class SyncFieldMappedWebController extends BaseController{
	
	private static final Logger log = Logger.getLogger(SyncFieldMappedWebController.class);
	
	@Resource
	private SyncFieldMappedService syncFieldMappedService;
	
	@ModelAttribute
	public SyncFieldMapped get(@RequestParam(required=false) String id) {
		SyncFieldMapped entity = null;
		if (Tool.isNotBlank(id)){
			entity = syncFieldMappedService.get(id);
		}
		if (entity == null){
			entity = new SyncFieldMapped();
		}
		return entity;
	}
	
	@RequiresPermissions("sync:field:mapped:mapped:view")
	@RequestMapping("/list")
	public String list(SyncFieldMapped syncFieldMapped,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<SyncFieldMapped> page = syncFieldMappedService.findPage(syncFieldMapped,request);
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/syncFieldMappedList.jsp";
	}
	
	@RequiresPermissions("sync:field:mapped:mapped:edit")
	@RequestMapping("/form")
	public String form(SyncFieldMapped syncFieldMapped, Model model) {
		model.addAttribute("entity", syncFieldMapped);
		return "/WEB-INF/views/project/syncFieldMappedForm.jsp";
	}
	
	@RequiresPermissions("sync:field:mapped:mapped:edit")
	@RequestMapping("/save")
	public String save(SyncFieldMapped syncFieldMapped, Model model, RedirectAttributes redirectAttributes) {
		syncFieldMappedService.save(syncFieldMapped);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/syncFieldMappedWebController/list";
	}
	
	@RequiresPermissions("sync:field:mapped:mapped:delete")
	@RequestMapping("/delete")
	public String delete(SyncFieldMapped syncFieldMapped, RedirectAttributes redirectAttributes) {
		syncFieldMappedService.delete(syncFieldMapped);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/syncFieldMappedWebController/list";
	}
}
