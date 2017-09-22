package dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dao.UserDao;
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

}
