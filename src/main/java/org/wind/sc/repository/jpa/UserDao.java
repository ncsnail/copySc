package org.wind.sc.repository.jpa;

import org.wind.sc.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User>{
	
	User findByLoginName(String loginName);
}
