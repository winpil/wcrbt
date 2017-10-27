package com.java.controller.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mangofactory.swagger.annotations.ApiIgnore;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java.sys.basic.controller.BaseController;
import com.java.sys.common.SysPage;
import com.java.entity.SyncTypeMapped;
import com.java.service.SyncProjectMappedService;
import com.java.service.SyncTypeMappedService;
import com.java.sys.utils.Tool;

@ApiIgnore
@Controller
@RequestMapping("/sys/syncTypeMappedWebController")
public class SyncTypeMappedWebController extends BaseController{
	@Resource
	private SyncTypeMappedService syncTypeMappedService;
	
	@Resource
	private SyncProjectMappedService syncProjectMappedService;
	
	@ModelAttribute
	public SyncTypeMapped get(@RequestParam(required=false) String id) {
		SyncTypeMapped entity = null;
		if (Tool.isNotBlank(id)){
			entity = syncTypeMappedService.get(id);
		}
		if (entity == null){
			entity = new SyncTypeMapped();
		}
		return entity;
	}
	
	@RequiresPermissions("sync:type:mapped:mapped:view")
	@RequestMapping("/list")
	public String list(SyncTypeMapped syncTypeMapped,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<SyncTypeMapped> page = syncTypeMappedService.findPage(syncTypeMapped,request);
		model.addAttribute("page", page);
		model.addAttribute("projects", syncProjectMappedService.getAll());
		return "/WEB-INF/views/project/syncTypeMappedList.jsp";
	}
	
	@RequiresPermissions("sync:type:mapped:mapped:edit")
	@RequestMapping("/form")
	public String form(SyncTypeMapped syncTypeMapped, Model model) {
		model.addAttribute("entity", syncTypeMapped);
		model.addAttribute("projects", syncProjectMappedService.getAll());
		return "/WEB-INF/views/project/syncTypeMappedForm.jsp";
	}
	
	@RequiresPermissions("sync:type:mapped:mapped:edit")
	@RequestMapping("/save")
	public String save(SyncTypeMapped syncTypeMapped, Model model, RedirectAttributes redirectAttributes) {
		syncTypeMappedService.save(syncTypeMapped);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/syncTypeMappedWebController/list";
	}
	
	@RequiresPermissions("sync:type:mapped:mapped:delete")
	@RequestMapping("/delete")
	public String delete(SyncTypeMapped syncTypeMapped, RedirectAttributes redirectAttributes) {
		syncTypeMappedService.delete(syncTypeMapped);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/syncTypeMappedWebController/list";
	}
}
