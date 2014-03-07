package org.wind.sc.service.user;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.wind.sc.entity.User;
import org.wind.sc.repository.jpa.UserJpaDao;
import org.wind.sc.to.SearchCriteria;


@Service("userServiceJpaImpl")
public class UserServiceJpaImpl implements IUserService{
	
	private static Logger log = LoggerFactory.getLogger(UserServiceJpaImpl.class);
	
	@Autowired
	UserJpaDao userDao;
	
	public User findByLoginName(String username){
		return userDao.findByLoginName(username);
	}
	
	public Page<User> getUserList(final SearchCriteria search,Pageable pa) throws Exception{
		
		Specification<User> spec =  new Specification<User>(){
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pAll = null;
				if(search != null){
					Predicate p1 = null;
					Predicate p2 = null;
					boolean b1 = StringUtils.isNotBlank(search.getLoginName()) ;
					boolean b2 = StringUtils.isNotBlank(search.getEmail());
					if(b1 && b2 ){
							p1 = cb.like(root.get("loginName").as(String.class), "%"+search.getLoginName()+"%");
							p2 = cb.like(root.get("email").as(String.class), "%"+search.getEmail()+"%");
							pAll = cb.and(p1,p2);
					}else if(b1 && !b2){
						pAll = cb.like(root.get("loginName").as(String.class), "%"+search.getLoginName()+"%");
					}else if(!b1 && b2){
						pAll = cb.like(root.get("email").as(String.class), "%"+search.getEmail()+"%");
					}
				}
				return pAll;
			}
		};
		
		return userDao.findAll(spec, pa);
	}
	
	public User getUser(Long userId){
		return this.userDao.findOne(userId);
	}
	
	public void saveUser(User user){
		this.userDao.save(user);
	}
	
}
