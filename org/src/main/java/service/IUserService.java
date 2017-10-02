package service;

import model.User;

public interface IUserService {
	
	public boolean validateUser(String username, String password);
	
	public boolean validateUserName(String username);
	
	public Integer getUserIdFromUsername(String username);
	
	public boolean validateEmail(String email);
	
	public void insertNewUser(String username,String email,String password,String address,String city,String country,String zip,String phoneNumber);
	
	public boolean isUserAdmin(String username);
	
	public User getUserFromUsername(String username);

}
