package org.wind.sc.repository.mybatis;

import java.util.List;

import org.wind.sc.entity.User;
import org.wind.sc.to.SearchPageCriteria;




@MyBatisRepository
public interface UserMyBatisDao {
	
	User get(Long id);
	
	User findByLoginName(String loginName);

	List<User> getList(SearchPageCriteria spc);
	
	int getAmount(SearchPageCriteria spc);

	void insert(User user);
	
	void update(User user);

	void delete(Long id);
}
