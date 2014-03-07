package org.wind.sc.repository.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.wind.sc.entity.User;

public interface UserJpaDao extends PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User> {
	
	User findByLoginName(String loginName);
}
