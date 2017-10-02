package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import model.LoginRequest;
import model.RegisterRequest;
import model.ResponseMessage;
import model.User;
import model.UserRequest;
import service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getMovieIndex() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView getRegistration() {
		return new ModelAndView("registration");
	}
	
	
	@RequestMapping(value="/login/{username}/{password}", method=RequestMethod.GET)
	public @ResponseBody ResponseMessage validateLogin(@PathVariable String username,@PathVariable String password, SessionStatus status,
	         HttpServletRequest request) {
		
		try {
		if(username==null || username.isEmpty() || password==null || password.isEmpty()) {
			 return new ResponseMessage("400","Incorrect Request");
		}
		else {
			boolean validation=userService.validateUser(username, password);
			if(validation) {
				status.setComplete();
				request.getSession().setAttribute("LOGGEDIN_USER", username);
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
	
	@RequestMapping(value="/logout",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody ResponseMessage logOutUser(@RequestBody LoginRequest loginRequest, SessionStatus status,
	         HttpServletRequest request) {
		ResponseMessage responseMessage=null;
		try {
			request.getSession().setAttribute("LOGGEDIN_USER",null);
			responseMessage=new ResponseMessage("200","Authenciated");
		}
		catch(Exception e) {
			responseMessage=new ResponseMessage("500","Internal Server Error");
		}
		return responseMessage;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody ResponseMessage registerUser(@RequestBody RegisterRequest registerRequest) {
		ResponseMessage response=null;
		try {
			if(userService.validateUserName(registerRequest.getUsername())) {
				response =new ResponseMessage("422","Username already exists");
			}
			else if(userService.validateEmail(registerRequest.getEmail())) {
				 response=new ResponseMessage("422","Email already exists");
			}
			else {
				userService.insertNewUser(registerRequest.getUsername(),registerRequest.getEmail(), registerRequest.getPassword(), 
						registerRequest.getAddress(), registerRequest.getCity(),registerRequest.getCountry(), registerRequest.getZip(),registerRequest.getPhoneNumber());
				response=new ResponseMessage("200","User created");
			}
		}
		catch(Exception e) {
			response=new ResponseMessage("500","Something went wrong try again");
		}
		return response;
	}
	

	@RequestMapping(value="/getDetailsOfUsers",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody User getDetailsOfUser(@RequestBody UserRequest request) {
		return userService.getUserFromUsername(request.getUsername());		
	}
	
}
