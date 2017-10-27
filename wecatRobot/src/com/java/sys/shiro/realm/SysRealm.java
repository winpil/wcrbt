package com.java.sys.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.java.sys.cache.EHCacheUtils;
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

//@Component
public class SysRealm extends AuthorizingRealm {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysRoleUserService sysRoleUserService;
	@Resource
	private SysRolePermService rolePermService;
	@Resource
	private SysRoleService roleService;
	@Resource
	private SysPermService permService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();
		if(sysUser == null){
			return null;
		}
		Map<String,Object> rolesMap = (Map<String, Object>) EHCacheUtils.get(EHCacheUtils.ROLES_MAP);
		Map<String,Object> permsMap = (Map<String, Object>) EHCacheUtils.get(EHCacheUtils.PERMS_MAP);
		Set<String> roles = (Set<String>) rolesMap.get(sysUser.getId());
		Set<String> perms = (Set<String>) permsMap.get(sysUser.getId());
		//Set<String> roles = new HashSet<String>();
		//Set<String> perms = new HashSet<String>();
		/*SysRoleUser _ru = new SysRoleUser(null, sysUser.getId());
		List<SysRoleUser> roleUserList = sysRoleUserService.findList(_ru);
		if(roleUserList != null && roleUserList.size()>0){
			for(SysRoleUser roleUser : roleUserList){
				if(roleUser != null){
					// 查询登陆用户所属的角色
					SysRole role = roleService.get(roleUser.getRoleId());
					if(role != null){
						roles.add(role.getName());
					}
					
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
		}*/
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		info.setStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		String password = new String((char[])token.getCredentials());
		
		SysUser _u = new SysUser(username, password, null, null, null);
		List<SysUser> list = sysUserService.findList(_u);
		if(list == null || list.size()<1){
			throw new UnknownAccountException("用户名或密码错误");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(list.get(0), password, this.getName());
		return info;
	}

}
