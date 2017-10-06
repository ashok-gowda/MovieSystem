package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value="/adminPanel/{username}",method=RequestMethod.GET)
	public ModelAndView getRegistration(@PathVariable String username) {
		ModelAndView adminView =new ModelAndView("admin");
		adminView.addObject("username",username);
		return adminView;
	}
}
