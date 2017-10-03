package jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import service.IMovieService;
import service.IRecommendationService;

public class SimilarityFinder{
	
	@Autowired
	IMovieService movieService;
	
	@Autowired
	IRecommendationService recommendationService;
	
	@Scheduled(cron="0 0 9 25 * ?")
	public void determineSimilarityBetweenPeople() {
		List<Integer> listOfUsers=movieService.getDistinctUsersWhoHaveCommented();
		for(int i=0;i<listOfUsers.size();i++) {
			int mostSimilarUserToI=0;
			double maxSimilarityScore=Double.MIN_VALUE;
			for(int j=i+1;j<listOfUsers.size();j++) {
				Integer userId1=listOfUsers.get(i);
				Integer userId2=listOfUsers.get(j);
				double similarityScore=recommendationService.generatePearsonsSimiliarityScoreForTwoUserIds(userId1, userId2);
				if(similarityScore>maxSimilarityScore) {
					maxSimilarityScore=similarityScore;
					mostSimilarUserToI=listOfUsers.get(j);
				}
			}
			recommendationService.saveOrUpdateMostSimilarUser(listOfUsers.get(i),mostSimilarUserToI);
		}
	}

}
