package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import model.ResponseMessage;
import service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/login/{username}/{password}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage validateLogin(@PathVariable String username,@PathVariable String password,ModelMap modelMap) {
		try {
		if(username==null || username.isEmpty() || password==null || password.isEmpty()) {
			return new ResponseMessage("400","Incorrect Request");
		}
		else {
			boolean validation=userService.validateUser(username, password);
			if(validation) {
				return new ResponseMessage("200","Authenciated");
			}
			else {
				return new ResponseMessage("200","Not Authenciated");
			}
		}
		}
		catch(Exception e) {
			return new ResponseMessage("500","Internal Server Error");
		}
	}

}
