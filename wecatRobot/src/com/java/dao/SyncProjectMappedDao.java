package com.java.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.entity.SyncProjectMapped;
import com.java.sys.basic.dao.BaseDao;


@Repository
public interface SyncProjectMappedDao extends BaseDao<SyncProjectMapped>{

	List<SyncProjectMapped> getAll();
	
}
