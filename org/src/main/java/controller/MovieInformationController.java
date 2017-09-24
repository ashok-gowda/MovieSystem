package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movieInformation")
public class MovieInformationController {
	
	@RequestMapping(value="/{username}/{movieId}",method=RequestMethod.GET)
	public ModelAndView getView(@PathVariable String username,@PathVariable String movieId) {
			ModelAndView modelView=new ModelAndView("movieinformation");
			modelView.addObject("username",username);
			modelView.addObject("movieId",movieId);
			return modelView;
	}
}
