package com.java.sys.rbac.controller.api;

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
import com.java.sys.rbac.controller.response.ReturnCity;
import com.java.sys.rbac.controller.response.ReturnDistrict;
import com.java.sys.rbac.controller.response.ReturnProvince;
import com.java.sys.rbac.entity.SysCity;
import com.java.sys.rbac.entity.SysDistrict;
import com.java.sys.rbac.entity.SysProvince;
import com.java.sys.rbac.service.SysCityService;
import com.java.sys.rbac.service.SysDistrictService;
import com.java.sys.rbac.service.SysProvinceService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api(value="api-province-controller",description = "省、市、区相关")
@Controller
@RequestMapping("/api/sysProvinceApiController")
public class ProvinceApiController extends BaseController{
	@Resource
	private SysProvinceService provinceService;
	@Resource
	private SysCityService cityService;
	@Resource
	private SysDistrictService districtService;
	
	/*
	 * 查询所有省份
	 */
	@ApiOperation(value = "查询所有省份", notes = "查询所有省份")
	@RequestMapping(value = "/findProvinceList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findProvinceList(HttpServletRequest request){
		List<SysProvince> provinceList = provinceService.findList(null);
		List<ReturnProvince> list = provinceService.getReturnProvinceList(provinceList);
		return buildSuccessInfo(list);
	}
	
	
	/*
	 * 查询所有城市或者根据省id查询城市
	 */
	@ApiOperation(value = "查询所有城市或者根据省id查询城市", notes = "查询所有城市或者根据省id查询城市")
	@RequestMapping(value = "/findCityList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findCityList(HttpServletRequest request,
			@ApiParam("省份id，可选") @RequestParam(value="provinceId",required=false) String provinceId){
		List<SysCity> cityList = cityService.findList(new SysCity(null, provinceId, null, null, null));
		List<ReturnCity> list = cityService.getReturnCityList(cityList);
		return buildSuccessInfo(list);
	}
	
	/*
	 * 查询所有地区或者根据城市id查询地区
	 */
	@ApiOperation(value = "查询所有地区或者根据城市id查询地区", notes = "查询所有地区或者根据城市id查询地区")
	@RequestMapping(value = "/findDistrictList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findDistrictList(HttpServletRequest request,
			@ApiParam("城市id，可选") @RequestParam(value="cityId",required=false) String cityId){
		List<SysDistrict> districtList = districtService.findList(new SysDistrict(null, cityId, null));
		List<ReturnDistrict> list = districtService.getReturnDistrictList(districtList);
		return buildSuccessInfo(list);
	}
	
}
