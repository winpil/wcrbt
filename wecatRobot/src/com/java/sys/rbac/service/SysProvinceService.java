package com.java.sys.rbac.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.java.sys.basic.service.BaseService;
import com.java.sys.rbac.controller.response.ReturnProvince;
import com.java.sys.rbac.dao.SysProvinceDao;
import com.java.sys.rbac.entity.SysProvince;
@Service
public class SysProvinceService extends BaseService<SysProvinceDao, SysProvince> {

	
	public ReturnProvince getReturnProvince(SysProvince sysProvince){
		ReturnProvince province = new ReturnProvince();
		try{
			if(sysProvince != null){
				BeanUtils.copyProperties(province,sysProvince);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return province;
	}
	
	
	public List<ReturnProvince> getReturnProvinceList(List<SysProvince> provinceList){
		List<ReturnProvince> list = new ArrayList<ReturnProvince>();
		if(provinceList != null && provinceList.size()>0){
			for(SysProvince province : provinceList){
				list.add(getReturnProvince(province));
			}
		}
		return list;
	}
	
}
