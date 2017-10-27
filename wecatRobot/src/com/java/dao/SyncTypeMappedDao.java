package com.java.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.java.entity.SyncTypeMapped;
import com.java.sys.basic.dao.BaseDao;


@Repository
public interface SyncTypeMappedDao extends BaseDao<SyncTypeMapped>{

	List<SyncTypeMapped> getAll();
	
}
