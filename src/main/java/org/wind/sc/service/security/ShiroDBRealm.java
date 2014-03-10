package org.wind.sc.service.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.wind.sc.entity.Role;
import org.wind.sc.entity.User;
import org.wind.sc.service.user.IUserService;
import org.wind.sc.to.ShiroUser;

public class ShiroDBRealm extends AuthorizingRealm{

	
	@Autowired
	IUserService userService;
	
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		ShiroUser su = (ShiroUser)principals.getPrimaryPrincipal();
		User user = userService.findByLoginName(su.getLoginName());
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		for(Role role: user.getRoleList()){
			info.addRole(role.getName());
			info.addStringPermissions(role.getPermissionList());
		}
		return info;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String username = token.getUsername();
		char[] password = token.getPassword();
		User user = userService.findByLoginName(username);
		if(user != null){
			if(username.equals(user.getLoginName())){
				if(new String(password).equals(user.getPassword())){
					//invalid account
					if("disabled".equals(user.getStatus())){
						throw new DisabledAccountException();
					}
					ShiroUser su =  new ShiroUser();
					su.setId(user.getId());
					su.setName(user.getName());
					su.setLoginName(user.getLoginName());
					
					return new SimpleAuthenticationInfo(su,password,user.getName()); 
				}
			}
		}
		return null;
	}

	

}
