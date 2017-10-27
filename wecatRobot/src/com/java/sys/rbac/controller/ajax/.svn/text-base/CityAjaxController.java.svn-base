package com.java.sys.rbac.controller.ajax;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.sys.basic.controller.BaseController;
import com.java.sys.basic.entity.BaseResult;
import com.java.sys.rbac.entity.SysCity;
import com.java.sys.rbac.service.SysCityService;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiParam;

@ApiIgnore
@Controller
@RequestMapping("/ajax/sysCityAjaxController")
public class CityAjaxController extends BaseController{
	@Resource
	private SysCityService cityService;
	
	/*
	 * 查询所有城市或者根据省id查询城市
	 */
	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findList(HttpServletRequest request,
			@ApiParam("省份id，可选") @RequestParam(value="provinceId",required=false) String provinceId){
		List<SysCity> list = cityService.findList(new SysCity(null, provinceId, null, null, null));
		return buildSuccessInfo(list);
	}
	
	
}
