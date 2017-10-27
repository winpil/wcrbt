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
import com.java.sys.rbac.entity.SysDistrict;
import com.java.sys.rbac.service.SysDistrictService;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiParam;

@ApiIgnore
@Controller
@RequestMapping("/ajax/sysDistrictAjaxController")
public class DistrictAjaxController extends BaseController{
	@Resource
	private SysDistrictService districtService;
	
	/*
	 * 查询所有地区或者根据城市id查询地区
	 */
	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findList(HttpServletRequest request,
			@ApiParam("城市id，可选") @RequestParam(value="cityId",required=false) String cityId){
		List<SysDistrict> list = districtService.findList(new SysDistrict(null, cityId, null));
		return buildSuccessInfo(list);
	}
	
}
