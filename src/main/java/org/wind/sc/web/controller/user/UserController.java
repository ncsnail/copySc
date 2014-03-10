package org.wind.sc.web.controller.user;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wind.sc.entity.User;
import org.wind.sc.service.user.IUserService;
import org.wind.sc.to.SearchCriteria;

import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private static final String PAGE_SIZE = "4";
	private static Map<String,String> allStatus = Maps.newHashMap();
	
	static{
		allStatus.put("enabled", "有效的");
		allStatus.put("disabled", "无效的");
	}
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getList(SearchCriteria search,
						  @RequestParam(value="pageNum",defaultValue="1") int pageNum,
						  @RequestParam(value="page.size",defaultValue=PAGE_SIZE)int pageSize, Model model){
		try{
			
			Pageable pa = buildPageRequest(pageNum, pageSize,null);
			Page<User> users = userService.getUserList(search, pa);
			
			StringBuffer sb = new StringBuffer();
			String loginNameValue = search.getLoginName();
			String emailValue = search.getEmail();
			if(loginNameValue == null){
				loginNameValue  =  "";
			}
			if(emailValue == null){
				emailValue = "" ;
			}
			sb.append("loginName=").append(loginNameValue);
			sb.append("&email=").append(emailValue);
			System.out.println(sb.toString());
			
			model.addAttribute("users", users);
			model.addAttribute("allStatus", allStatus);
			model.addAttribute("search", search);
			model.addAttribute("searchParams", sb.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "user/list";
	}
	
	@RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
	public String getUser(@PathVariable("id") Long userId,Model model){
		try{
			User user  = userService.getUser(userId);
			if(user != null){
				model.addAttribute("user", user);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "user/form";
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") User user,RedirectAttributes redirectAttributes){
		
		try{
			User oldUser  = userService.getUser(user.getId());
			oldUser.setLoginName(user.getLoginName());
			oldUser.setName(user.getName());
			oldUser.setEmail(user.getEmail());
			if(StringUtils.isNotBlank(user.getPlainPassword())){
				oldUser.setPassword(user.getPlainPassword());
			}
			userService.saveUser(oldUser);
			redirectAttributes.addAttribute("message", "用户更新成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("oldLoginName")String oldLoginName,@RequestParam("loginName")String loginName){
		if(oldLoginName.equals(loginName)){
			return "true"; 
		}
		if(userService.findByLoginName(loginName) == null){
			return "true";
		}
		return "false";
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
	
	
}
