package service;

public interface IUserService {
	
	public boolean validateUser(String username, String password);
	
	public boolean validateUserName(String username);
	
	public Integer getUserIdFromUsername(String username);

}
