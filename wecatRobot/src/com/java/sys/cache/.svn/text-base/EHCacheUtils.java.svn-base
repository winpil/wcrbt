package com.java.sys.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.java.sys.rbac.entity.SysPerm;
import com.java.sys.rbac.entity.SysRole;
import com.java.sys.rbac.entity.SysRolePerm;
import com.java.sys.rbac.entity.SysRoleUser;
import com.java.sys.rbac.entity.SysUser;
import com.java.sys.rbac.service.SysPermService;
import com.java.sys.rbac.service.SysRolePermService;
import com.java.sys.rbac.service.SysRoleService;
import com.java.sys.rbac.service.SysRoleUserService;
import com.java.sys.rbac.service.SysUserService;
import com.java.sys.utils.SpringContextHolder;

/**
 * 全局EHCache缓存类
 */
public class EHCacheUtils {
	public static final String ROLES_MAP = "roles_map";
	public static final String PERMS_MAP = "perms_map";
	
	/*
	 * 缓存对象
	 */
	public static Cache cache = null;
	/*
	 * 业务层注入
	 */
	protected static SysRolePermService rolePermService = SpringContextHolder.getBean(SysRolePermService.class);
	protected static SysRoleUserService roleUserService = SpringContextHolder.getBean(SysRoleUserService.class);
	protected static SysRoleService roleService = SpringContextHolder.getBean(SysRoleService.class);
	protected static SysPermService permService = SpringContextHolder.getBean(SysPermService.class);
	protected static SysUserService userService = SpringContextHolder.getBean(SysUserService.class);
	
	/**
	 * 初始化ehcache
	 */
	public static void init(){
		System.out.println("----- EHCacheUtils -> init() "+new Date().toLocaleString());
		if(EHCacheUtils.cache == null){
			EHCacheUtils.cache = CacheManager.create().getCache("cache_java");
		}
		refreshRealm();
	}
	
	/**
	 * 放对象
	 * @param key
	 * @param obj
	 */
	public static void put(String key,Object obj){
		if(obj != null){
			if(EHCacheUtils.cache == null){
				EHCacheUtils.init();
			}
			Element element = new Element(key,obj);
			EHCacheUtils.cache.put(element);
		}
		System.out.println("----- EHCacheUtils : after put "+new Date().toLocaleString());
	}
	
	/**
	 * 方法名：put
	 * 详述：放对象
	 * @param key
	 * @param obj
	 * @param liveTime 存活时间，秒
	 */
	public static void put(String key,Object obj,int liveTime){
		if(obj != null){
			if(EHCacheUtils.cache == null){
				EHCacheUtils.init();
			}
			Element element = new Element(key, obj, true, null, liveTime);
			EHCacheUtils.cache.put(element);
		}
		System.out.println("----- EHCacheUtils : after put "+new Date().toLocaleString());
	}
	
	/**
	 * 取对象
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		if(EHCacheUtils.cache == null){
			return null;
		}
		Element element = EHCacheUtils.cache.get(key);
		if(element == null){
			return null;
		}
		return element.getValue();
	}
	
	
	/**
	 * 方法名：remove
	 * 详述：删除缓存数据
	 * @param key
	 */
	public static void remove(String key){
		if(EHCacheUtils.cache != null){
			Element element = EHCacheUtils.cache.get(key);
			if(element != null){
				EHCacheUtils.cache.removeElement(element);
			}
		}
	}
	
	
	
	/**
	 * 打印cache信息
	 */
	public static void printInfo(){
		if(EHCacheUtils.cache == null){
			System.out.println("----- EHCacheUtils -> printInfo() : cache is null !");
		}else{
			int size = EHCacheUtils.cache.getSize();	//得到缓存中的对象数
			Long memoryStoreSize = EHCacheUtils.cache.getMemoryStoreSize();		//得到缓存对象占用内存的大小
			Long cacheHits = EHCacheUtils.cache.getStatistics().getCacheHits();		//得到缓存读取的命中次数
			Long cacheMisses = EHCacheUtils.cache.getStatistics().getCacheMisses();		//得到缓存读取的错失次数
			System.out.println("----- EHCacheUtils -> printInfo() size:"+size+",memoryStoreSize:"+memoryStoreSize+",cacheHits:"+cacheHits+",cacheMisses:"+cacheMisses);
		}
	}
	
	/**
	 * 刷新缓存中的角色和权限
	 */
	public static void refreshRealm(){
		System.out.println("----- EHCacheUtils -> refreshRealm() "+new Date().toLocaleString());
		Map<String,Object> rolesMap = new HashMap<String,Object>();
		Map<String,Object> permsMap = new HashMap<String,Object>();
		if(EHCacheUtils.cache == null){
			EHCacheUtils.init();
		}
		List<SysUser> userList = userService.findList(null);
		if(userList != null && userList.size()>0){
			for(SysUser user : userList){
				Set<String> roles = new HashSet<String>();
				Set<String> perms = new HashSet<String>();
				// 查询用户拥有的角色
				SysRoleUser _ru = new SysRoleUser(null, user.getId());
				List<SysRoleUser> ruList = roleUserService.findList(_ru);
				if(ruList != null && ruList.size()>0){
					for(SysRoleUser ru : ruList){
						SysRole role = roleService.get(ru.getRoleId());
						if(role != null){
							roles.add(role.getName());
							// 查询角色拥有的权限
							SysRolePerm _rp = new SysRolePerm(role.getId(), null);
							List<SysRolePerm> rolePermList = rolePermService.findList(_rp);
							if(rolePermList != null && rolePermList.size()>0){
								for(SysRolePerm rolePerm : rolePermList){
									SysPerm perm = permService.get(rolePerm.getPermId());
									if(perm != null){
										perms.add(perm.getPermission());
									}
								}
							}
						}
					}
				}
				rolesMap.put(user.getId(), roles);
				permsMap.put(user.getId(), perms);
			}
		}
		EHCacheUtils.put(ROLES_MAP, rolesMap);
		EHCacheUtils.put(PERMS_MAP, permsMap);
	}
	
	
	
	public static Cache getCache() {
		return cache;
	}
	public static void setCache(Cache cache) {
		EHCacheUtils.cache = cache;
	}
	
}
