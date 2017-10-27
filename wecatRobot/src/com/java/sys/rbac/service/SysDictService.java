package com.java.sys.rbac.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.sys.basic.service.BaseService;
import com.java.sys.rbac.dao.SysDictDao;
import com.java.sys.rbac.entity.SysDict;


@Service
@Transactional(readOnly = true)
public class SysDictService extends BaseService<SysDictDao, SysDict> {

	
}