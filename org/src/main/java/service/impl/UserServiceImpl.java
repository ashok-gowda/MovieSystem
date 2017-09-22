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

}
