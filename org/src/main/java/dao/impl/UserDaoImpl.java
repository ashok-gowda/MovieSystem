package dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dao.UserDao;
import model.User;
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private DataSource datasource;

	private JdbcTemplate jdbcTemplate;
	
	
	
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}





	public boolean validateUser(String username, String password) {
		jdbcTemplate = new JdbcTemplate(datasource);
		String sql="Select count(*) from users where username=? and password=?";
		Integer numberOfUsers=jdbcTemplate.queryForObject(sql,new Object[] {username,password},Integer.class);
		if(numberOfUsers==1) {
			return true;
		}
		return false;
	}
	
	public boolean validateUserName(String username) {
		jdbcTemplate = new JdbcTemplate(datasource);
		String sql="Select count(*) from users where username=?";
		Integer numberOfUsers=jdbcTemplate.queryForObject(sql,new Object[] {username},Integer.class);
		if(numberOfUsers==1) {
			return true;
		}
		return false;
		
	}
	
	
	public boolean validateEmail(String email) {
		jdbcTemplate = new JdbcTemplate(datasource);
		String sql="Select count(*) from users where email=?";
		Integer numberOfUsers=jdbcTemplate.queryForObject(sql,new Object[] {email},Integer.class);
		if(numberOfUsers==1) {
			return true;
		}
		return false;		
	}
	
	@Override
	public Integer getUserIdFromUserName(String username) {
		jdbcTemplate = new JdbcTemplate(datasource);
		String sql="Select id from users where username=?";
		Integer userId=jdbcTemplate.queryForObject(sql,new Object[] {username},Integer.class);
		return userId;
	}





	@Override
	public void insertNewUser(String username, String email, String password, String address, String city,
			String country, String zip, String phoneNumber) {
		String sql="Insert into users(username,password,email,phone,address,city,zip,country) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update(sql,new Object[] {username,password,email,phoneNumber,address,city,zip,country});
	}





	@Override
	public boolean isUserAdmin(String username) {
		jdbcTemplate = new JdbcTemplate(datasource);
		String sql="Select isAdmin from users where username=?";
		Boolean isAdmin=jdbcTemplate.queryForObject(sql,new Object[] {username},Boolean.class);
		return isAdmin;
	}





	@Override
	public User getUserFromUsername(String username) {
		jdbcTemplate=new JdbcTemplate(datasource);
		String sql="Select * from users where username LIKE ?";
		username="%"+username+"%";
		List<Map<String,Object>> rows=jdbcTemplate.queryForList(sql,new Object[] {username});
		User user=new User();
		user.setId((Integer)rows.get(0).get("id"));
		user.setIsAdmin((Boolean)rows.get(0).get("isAdmin"));
		return user;
	}





	

}
