package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.SyncLog;
import com.java.dao.SyncLogDao;
import com.java.sys.basic.service.BaseService;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class SyncLogService extends BaseService<SyncLogDao, SyncLog> {

	
	public SyncLog getReturnLog(SyncLog entity,HttpServletRequest request){
		SyncLog log = new SyncLog();
		if(entity != null){
			try{
				BeanUtils.copyProperties(log, entity);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return log;
	}
	
	
	public List<SyncLog> getReturnLogList(List<SyncLog> entityList,HttpServletRequest request){
		List<SyncLog> list = new ArrayList<SyncLog>();
		if(entityList != null && entityList.size()>0){
			for(SyncLog entity : entityList){
				list.add(getReturnLog(entity, request));
			}
		}
		return list;
	}
	
}