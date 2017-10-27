package com.java.controller.web;

import java.util.ArrayList;
import java.util.List;

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
import com.java.entity.Project;
import com.java.entity.SyncProjectMapped;
import com.java.service.SyncProjectMappedService;
import com.java.sys.utils.Tool;

@ApiIgnore
@Controller
@RequestMapping("/sys/syncProjectMappedWebController")
public class SyncProjectMappedWebController extends BaseController{
	@Resource
	private SyncProjectMappedService syncProjectMappedService;
	
	public static List<Project> list =new ArrayList<>();
	
	@ModelAttribute
	public SyncProjectMapped get(@RequestParam(required=false) String id) {
		SyncProjectMapped entity = null;
		if (Tool.isNotBlank(id)){
			entity = syncProjectMappedService.get(id);
		}
		if (entity == null){
			entity = new SyncProjectMapped();
		}
		return entity;
	}
	
	@RequiresPermissions("sync:project:mapped:mapped:view")
	@RequestMapping("/list")
	public String list(SyncProjectMapped syncProjectMapped,Model model,HttpServletRequest request,HttpServletResponse response){
		SysPage<SyncProjectMapped> page = syncProjectMappedService.findPage(syncProjectMapped,request);
		model.addAttribute("page", page);
		return "/WEB-INF/views/project/syncProjectMappedList.jsp";
	}
	
	@RequiresPermissions("sync:project:mapped:mapped:edit")
	@RequestMapping("/form")
	public String form(SyncProjectMapped syncProjectMapped, Model model) {
		model.addAttribute("entity", syncProjectMapped);
		list = syncProjectMappedService.getProjcetList();
		model.addAttribute("projects", list);
		return "/WEB-INF/views/project/syncProjectMappedForm.jsp";
	}
	
	@RequiresPermissions("sync:project:mapped:mapped:edit")
	@RequestMapping("/save")
	public String save(SyncProjectMapped syncProjectMapped, Model model, RedirectAttributes redirectAttributes) {
		if(Tool.isBlank(syncProjectMapped.getId())){
			SyncProjectMapped p =syncProjectMappedService.getBy("project_id", syncProjectMapped.getProjectId().toString());
			if(null!=p){
				addMessage("保存失败，此项目已设置映射", ERROR, redirectAttributes);
				return "redirect:/sys/syncProjectMappedWebController/list";
			}
		}
		for(Project p : list){
			if(p.getId().equals(syncProjectMapped.getProjectId().toString())){
				syncProjectMapped.setProjectName(p.getName());
				break;
			}
		}
		syncProjectMappedService.save(syncProjectMapped);
		addMessage("保存成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/syncProjectMappedWebController/list";
	}
	
	@RequiresPermissions("sync:project:mapped:mapped:delete")
	@RequestMapping("/delete")
	public String delete(SyncProjectMapped syncProjectMapped, RedirectAttributes redirectAttributes) {
		syncProjectMappedService.delete(syncProjectMapped);
		addMessage("删除成功", SUCCESS, redirectAttributes);
		return "redirect:/sys/syncProjectMappedWebController/list";
	}
}
