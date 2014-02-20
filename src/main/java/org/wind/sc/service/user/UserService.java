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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
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
	
	public Page<User> getUserList(String[] searchParams,int pageNum,int pageSize){
		
		PageRequest pr = buildPageRequest(pageNum,pageSize,null);
		Specification<User> spec =  buildSpecification(searchParams); 
		return userDao.findAll(spec, pr);
	}
	//build page info
	private PageRequest buildPageRequest(int pageNum,int pageSize,String sortType){
		Sort sort = null;
		if(sortType!=null){
			//to do
		}else{
			sort = new Sort(Direction.ASC,"id");
		}
		return new PageRequest(pageNum - 1,pageSize,sort);
	}
	//build search criteria
	private Specification<User> buildSpecification(final String[] searchParams){
		return new Specification<User>(){
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p = null;
				if(searchParams != null){
					Predicate pt = null;
					for(int i = 0; i< searchParams.length;i++){
						if(StringUtils.isNotBlank(searchParams[i])){
							pt = cb.like(root.get("loginName").as(String.class), "%"+searchParams[i]+"%");
							p = cb.and(p,pt);
						}
					}
				}
				return p;
			}
		};
	}
	
	
	
	
}
