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
import com.java.sys.rbac.entity.SysProvince;
import com.java.sys.rbac.service.SysProvinceService;
import com.mangofactory.swagger.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/ajax/sysProvinceAjaxController")
public class ProvinceAjaxController extends BaseController{
	@Resource
	private SysProvinceService provinceService;
	
	/*
	 * 查询所有省份
	 */
	@RequestMapping(value = "/findAllList123", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findAllList(HttpServletRequest request){
		List<SysProvince> list = provinceService.findList(null);
		return buildSuccessInfo(list);
	}
}
