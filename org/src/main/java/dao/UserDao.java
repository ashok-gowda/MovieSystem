package dao;

public interface UserDao {
	
	public boolean validateUser(String username, String password);
	
	public boolean validateUserName(String username);
	
	public Integer getUserIdFromUserName(String username);

}
