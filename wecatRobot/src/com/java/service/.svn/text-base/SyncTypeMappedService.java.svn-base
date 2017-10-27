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

import com.java.entity.Project;
import com.java.entity.SyncProjectMapped;
import com.java.entity.SyncTypeMapped;
import com.java.dao.SyncTypeMappedDao;
import com.java.sys.basic.service.BaseService;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SyncTypeMappedService extends BaseService<SyncTypeMappedDao, SyncTypeMapped> {

	
	public SyncTypeMapped getSyncTypeMapped(SyncTypeMapped entity,HttpServletRequest request){
		SyncTypeMapped typemapped = new SyncTypeMapped();
		if(entity != null){
			try{
				BeanUtils.copyProperties(typemapped, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return typemapped;
	}
	
	
	public List<SyncTypeMapped> getSyncTypeMappedList(List<SyncTypeMapped> entityList,HttpServletRequest request){
		List<SyncTypeMapped> list = new ArrayList<SyncTypeMapped>();
		if(entityList != null && entityList.size()>0){
			for(SyncTypeMapped entity : entityList){
				list.add(getSyncTypeMapped(entity, request));
			}
		}
		return list;
	}


	public Map<String,String> getMapped() {
		Map<String,String> map = new HashMap<>();
		List<SyncTypeMapped> projects = dao.getAll();
		for(SyncTypeMapped t: projects){
			map.put(t.getMappedValue(), t.getTypeId().toString());
		}
		return map;
	}
	
}