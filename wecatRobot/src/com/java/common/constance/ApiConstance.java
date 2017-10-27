package com.java.common.constance;

import com.java.sys.common.constance.SysConstance;

public class ApiConstance extends SysConstance{
	
	
	public static String getMessage(int errorCode){
		return map.get(errorCode);
	}
	
	public final static int ROLE_NOT_EXIST = 2001;
	public final static int STREET_NOT_EXIST = 2002;
	public final static int AREA_NOT_EXIST = 2003;
	public final static int GRID_NOT_EXIST = 2004;
	public final static int GROUP_NOT_EXIST = 2005;
	public final static int PERM_NOT_EXIST = 2006;
	
	
	
	static{
		map.put(ROLE_NOT_EXIST, "角色不存在");
		map.put(STREET_NOT_EXIST, "街道不存在");
		map.put(AREA_NOT_EXIST, "片区不存在");
		map.put(GRID_NOT_EXIST, "网格不存在");
		map.put(GROUP_NOT_EXIST, "巡查组不存在");
		map.put(PERM_NOT_EXIST, "权限不存在");
	}
	
	
	
}
