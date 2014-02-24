package org.wind.sc.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wind.sc.entity.User;
import org.wind.sc.service.user.UserService;
import org.wind.sc.to.SearchAttributes;

import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private static final String PAGE_SIZE = "4";
	private static Map<String,String> allStatus = Maps.newHashMap();
	
	static{
		allStatus.put("enable", "有效的");
		allStatus.put("unable", "无效的");
	}
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getList(SearchAttributes search,
						  @RequestParam(value="page",defaultValue="1") int pageNum,
						  @RequestParam(value="page.size",defaultValue=PAGE_SIZE)int pageSize, Model model,ServletRequest request){
		
		Page<User> users = userService.getUserList(search, pageNum, pageSize);
		request.setAttribute("users", users);
		request.setAttribute("allStatus", allStatus);
		
		return "user/list";
	}
	
	
}
