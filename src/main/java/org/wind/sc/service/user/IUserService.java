package org.wind.sc.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wind.sc.entity.User;
import org.wind.sc.to.SearchCriteria;


public interface IUserService {

	public User findByLoginName(String username);
	
	public Page<User> getUserList(SearchCriteria search,Pageable pa) throws Exception;
	
	public User getUser(Long userId);
	
	public void saveUser(User user);
	
}
