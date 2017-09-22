package org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping(value="/login/{username}/{password}", method=RequestMethod.GET)
	public String validateLogin(@PathVariable String username,@PathVariable String password) {
		if()
	}

}
