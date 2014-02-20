package org.wind.sc.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	
	
	@RequestMapping(value = "/user")
	public String getList(){
		
		return "";
	}
	
	
}
