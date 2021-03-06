package service;

public interface IRecommendationService {
	
	public double generatePearsonsSimiliarityScoreForTwoUserIds(Integer user1,Integer user2);
	
	public void saveOrUpdateMostSimilarUser(Integer user1,Integer mostSimilarUser);
	
	public boolean checkIfUserIdIsPresent(Integer user1);
	
	public Integer getSimilarUserToUserId(Integer user1);

}
