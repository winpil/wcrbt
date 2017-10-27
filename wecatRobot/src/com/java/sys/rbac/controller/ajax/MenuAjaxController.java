package com.java.sys.rbac.controller.ajax;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.sys.basic.controller.BaseController;
import com.java.sys.basic.entity.BaseResult;
import com.java.sys.rbac.entity.SysMenu;
import com.java.sys.rbac.service.SysMenuService;
import com.mangofactory.swagger.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/ajax/menuAjaxController")
public class MenuAjaxController extends BaseController{
	@Resource
	private SysMenuService menuService;
	
	
	@RequestMapping(value = "/findAllList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findAllList(HttpServletRequest request){
		List<SysMenu> list = menuService.findListOrderByLevel();
		if(list != null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				if(!menuService.hasViewPerm(list.get(i))){
					list.remove(i);
					i -= 1;
				}
			}
		}
		return buildSuccessInfo(list);
	}
	
	
	
	@RequestMapping(value = "/findAllListTree", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findAllListTree(HttpServletRequest request){
		List<SysMenu> list = menuService.findListOrderByLevel();
		return buildSuccessInfo(list);
	}
	
}
