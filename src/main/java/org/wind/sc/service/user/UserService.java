package org.wind.sc.service.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wind.sc.entity.User;
import org.wind.sc.repository.jpa.UserDao;


@Service("userService")
public class UserService {
	
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDao userDao;
	
public User findByLoginName(String username){
		
		return userDao.findByLoginName(username);
	}
}
