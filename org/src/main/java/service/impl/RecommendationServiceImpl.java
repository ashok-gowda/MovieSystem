package service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IRecommendationDao;
import model.RatingModel;
import service.IMovieService;
import service.IRecommendationService;

@Service("RecommendationServiceImpl")
public class RecommendationServiceImpl implements IRecommendationService {

	@Autowired
	IMovieService movieService;
	
	@Autowired
	IRecommendationDao recommendationDao;

	@Override
	public double generatePearsonsSimiliarityScoreForTwoUserIds(Integer user1, Integer user2) {
		double similarityScore=0;
		Map<Integer,Integer> mapOfUser1Ratings=movieService.getMappingOfMoviesCommentedWithRatingsForUser(String.valueOf(user1));
		Map<Integer,Integer> mapOfUser2Ratings=movieService.getMappingOfMoviesCommentedWithRatingsForUser(String.valueOf(user2));
		Map<Integer,RatingModel> mapOfRatingsForSimilarMovies=new HashMap<Integer,RatingModel>();
		for(Integer movieId:mapOfUser1Ratings.keySet()) {
			if(mapOfUser2Ratings.get(movieId)!=null) {
				mapOfRatingsForSimilarMovies.put(movieId,new RatingModel(mapOfUser1Ratings.get(movieId), mapOfUser2Ratings.get(movieId)));
			}
		}
		if(mapOfRatingsForSimilarMovies.size()!=0) {
		int n=mapOfRatingsForSimilarMovies.size();
		double sumOfUser1Ratings=0;
		double sumOfUser2Ratings=0;
		double sum1Sq=0;
		double sum2Sq=0;
		double pSum=0;
		for(Integer movieId:mapOfRatingsForSimilarMovies.keySet()) {
			sumOfUser1Ratings+=mapOfRatingsForSimilarMovies.get(movieId).getRatingOfUser1();
			sumOfUser2Ratings+=mapOfRatingsForSimilarMovies.get(movieId).getRatingOfUser2();
			sum1Sq+=Math.pow(mapOfRatingsForSimilarMovies.get(movieId).getRatingOfUser1(),2);
			sum2Sq+=Math.pow(mapOfRatingsForSimilarMovies.get(movieId).getRatingOfUser2(),2);
			pSum+=mapOfRatingsForSimilarMovies.get(movieId).getRatingOfUser1()*mapOfRatingsForSimilarMovies.get(movieId).getRatingOfUser2();
		}
		double num=pSum-(sumOfUser1Ratings*sumOfUser2Ratings/mapOfRatingsForSimilarMovies.size());
		double den=Math.sqrt((sum1Sq-Math.pow(sumOfUser1Ratings,2)/n)*(sum2Sq-Math.pow(sumOfUser2Ratings,2)/n));
		if(den!=0) {
			similarityScore=num/den;
		}
		}
		return similarityScore;
	}

	@Override
	public void saveOrUpdateMostSimilarUser(Integer user1, Integer mostSimilarUser) {
		recommendationDao.saveOrUpdateMostSimilarUser(user1, mostSimilarUser);	
	}

	@Override
	public boolean checkIfUserIdIsPresent(Integer user1) {
		return recommendationDao.checkIfUserIdIsPresent(user1);
	}

	@Override
	public Integer getSimilarUserToUserId(Integer user1) {
		return recommendationDao.getSimilarUserToUserId(user1);
	}

}
