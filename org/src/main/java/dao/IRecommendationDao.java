package dao;

public interface IRecommendationDao {

	public void saveOrUpdateMostSimilarUser(Integer user1,Integer mostSimilarUser);
	
	public boolean checkIfUserIdIsPresent(Integer user1);
	
	public Integer getSimilarUserToUserId(Integer user1);
}
