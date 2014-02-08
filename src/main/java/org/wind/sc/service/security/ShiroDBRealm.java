package org.wind.sc.service.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.wind.sc.entity.User;

public class ShiroDBRealm extends AuthorizingRealm{

	
	//@Autowired
	//UserDao userDao;
	
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		//ShiroUser su = (ShiroUser)principals.getPrimaryPrincipal();
		//User user = userDao.findByLoginName(username);
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//info.addRole(su.getRoles());
		return info;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String username = token.getUsername();
		char[] password = token.getPassword();
		//User user = userDao.findByLoginName(username);
		//ShiroUser su =  new ShiroUser();
		//su.setId(user.getId());
		//su.setName(user.getName());
		//su.setLoginName(user.getLoginName());
		//su.setRoles(user.getRoles());
		
		User user = new User();
		user.setLoginName("user");
		user.setPassword("user");
		if(user != null){
			if(username.equals(user.getLoginName())){
				if(new String(password).equals(user.getPassword())){
					return new SimpleAuthenticationInfo(user,password,user.getName()); 
				}
			}
		}
		return null;
	}

	

}
