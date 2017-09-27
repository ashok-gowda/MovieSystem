package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import service.IUserService;
@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	 UserDao userDao;

	public boolean validateUser(String username, String password) {
		return userDao.validateUser(username, password);
	}

	@Override
	public boolean validateUserName(String username) {
		return userDao.validateUserName(username);
	}

	@Override
	public Integer getUserIdFromUsername(String username) {
		return userDao.getUserIdFromUserName(username);
	}

	@Override
	public boolean validateEmail(String email) {
		return userDao.validateEmail(email);
	}

	@Override
	public void insertNewUser(String username, String email, String password, String address, String city,
			String country, String zip, String phoneNumber) {
		userDao.insertNewUser(username, email, password, address, city, country, zip, phoneNumber);
		
		
	}

}
