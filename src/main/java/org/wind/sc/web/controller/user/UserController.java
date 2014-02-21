package org.wind.sc.web.controller.user;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	
	
	@RequestMapping(value = "/user")
	public String getList(Model model,ServletRequest request){
		
		
		
		return "";
	}
	
	
}
