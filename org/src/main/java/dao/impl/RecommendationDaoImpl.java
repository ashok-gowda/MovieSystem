package dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dao.IRecommendationDao;

@Repository("RecommendationDaoImpl")
public class RecommendationDaoImpl implements IRecommendationDao {
	
	@Autowired
	private DataSource datasource;

	@Override
	public void saveOrUpdateMostSimilarUser(Integer user1, Integer mostSimilarUser) {
		Integer userId=getIdIfExistsInSimilarityTable(user1);
		System.out.println(user1+""+mostSimilarUser);
		if(userId==null) {
			String sql="Insert into user_similarity(user_id,similar_user_id) VALUES (?,?)";
			JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
			jdbcTemplate.update(sql,new Object[] {String.valueOf(user1),String.valueOf(mostSimilarUser)});
		}
		else {
			String sql="Update user_similarity SET similar_user_id=? where user_id=?";
			JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
			jdbcTemplate.update(sql,new Object[] {String.valueOf(mostSimilarUser),String.valueOf(user1)});
		}
	}
	
	
	public Integer getIdIfExistsInSimilarityTable(Integer user1) {
			String sql="Select id from user_similarity where user_id=?";
			JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
			Integer userId=jdbcTemplate.queryForObject(sql,new Object[] {String.valueOf(user1)},Integer.class);
			return userId;
	}


	@Override
	public boolean checkIfUserIdIsPresent(Integer user1) {
		String sql="Select count(*) from user_similarity where user_id=?";
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		Integer count=jdbcTemplate.queryForObject(sql,new Object[] {String.valueOf(user1)},Integer.class);
		if(count==null ||count==0) {
			return false;
		}
		return true;
	}


	@Override
	public Integer getSimilarUserToUserId(Integer user1) {
			String sql="Select similar_user_id from user_similarity where user_id=?";
			JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
			Integer userId=jdbcTemplate.queryForObject(sql,new Object[] {String.valueOf(user1)},Integer.class);
			return userId;
	}

}
