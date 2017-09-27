package dao;

public interface UserDao {
	
	public boolean validateUser(String username, String password);
	
	public boolean validateUserName(String username);
	
	public boolean validateEmail(String email);
	
	public Integer getUserIdFromUserName(String username);
	
	public void insertNewUser(String username,String email,String password,String address,String city,String country,String zip,String phoneNumber);

}
