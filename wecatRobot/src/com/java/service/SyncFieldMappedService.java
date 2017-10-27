package com.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.SyncFieldMapped;
import com.java.dao.SyncFieldMappedDao;
import com.java.sys.basic.service.BaseService;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SyncFieldMappedService extends BaseService<SyncFieldMappedDao, SyncFieldMapped> {

	
	public SyncFieldMapped getReturnFieldMapped(SyncFieldMapped entity,HttpServletRequest request){
		SyncFieldMapped fieldmapped = new SyncFieldMapped();
		if(entity != null){
			try{
				BeanUtils.copyProperties(fieldmapped, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return fieldmapped;
	}
	
	
	public List<SyncFieldMapped> getReturnFieldMappedList(List<SyncFieldMapped> entityList,HttpServletRequest request){
		List<SyncFieldMapped> list = new ArrayList<SyncFieldMapped>();
		if(entityList != null && entityList.size()>0){
			for(SyncFieldMapped entity : entityList){
				list.add(getReturnFieldMapped(entity, request));
			}
		}
		return list;
	}


	public Map<String,String> getMapped() {
		Map<String,String> map = new HashMap<>();
		List<SyncFieldMapped> fields = dao.getAll();
		for(SyncFieldMapped f: fields){
			map.put(f.getMappedValue(), f.getFieldId());
		}
		return map;
	}


	public Map<String, String> getTypeMapped() {
		Map<String,String> map = new HashMap<>();
		List<SyncFieldMapped> fields = dao.getAll();
		for(SyncFieldMapped f: fields){
			map.put(f.getFieldId(), f.getFieldType());
		}
		return map;
	}
}