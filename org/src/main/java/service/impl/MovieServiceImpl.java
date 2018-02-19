package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IMovieDao;
import model.Comment;
import model.Movie;
import model.MovieWithComments;
import model.RatingModel;
import model.User;
import service.IMovieService;
import service.IRecommendationService;
import service.IUserService;

@Service("MovieServiceImpl")
public class MovieServiceImpl implements IMovieService {
	
	@Autowired
	IMovieDao movieDao;
	
	@Autowired
	IRecommendationService recommendationService;
	
	@Autowired
	IUserService userService;

	@Override
	public List<Movie> getListOfMoviesByGenre(String genre) {
		return movieDao.getListOfMoviesByGenre(genre);
	}

	@Override
	public List<Movie> getListOfMoviesByLanguage(String language) {
		return movieDao.getListOfMoviesByLanguage(language);
	}

	@Override
	public List<Movie> getListOfMoviesByReleaseRange(String category) {
		Calendar c=Calendar.getInstance();
		List<Movie> listOfMovies=null;
		switch(category) {
		case "1970-1980":listOfMovies=movieDao.getListOfMoviesByReleaseRange(new LocalDate(1970,1,1).toDate(), new LocalDate(1980,12,31).toDate());
						break;
		case "1980-1990":listOfMovies=movieDao.getListOfMoviesByReleaseRange(new LocalDate(1981,1,1).toDate(), new LocalDate(1990,12,31).toDate());
						break;
		case "1990-2000":listOfMovies=movieDao.getListOfMoviesByReleaseRange(new LocalDate(1991,1,1).toDate(), new LocalDate(2000,12,31).toDate());
		                break;
		case "2000-2010":listOfMovies=movieDao.getListOfMoviesByReleaseRange(new LocalDate(2001,1,1).toDate(), new LocalDate(2010,12,31).toDate());
		                break;
		case "After2010":listOfMovies=movieDao.getListOfMoviesByReleaseRange(new LocalDate(2011,1,1).toDate(), new Date());
		                break;
		}
		return listOfMovies;
	}

	@Override
	public List<Movie> getListOfMoviesBySearchTerm(String term) {
		List<Movie> listOfMovies=movieDao.getListOfMoviesBySearchTitle(term);
		if(listOfMovies.size()==0) {
			listOfMovies=movieDao.getListOfMoviesBySearchArtist(term);
		}
		return listOfMovies;
	}

	@Override
	public Movie getMovieInformationById(String id) {
		return movieDao.getMovieInformationById(id);
	}

	@Override
	public MovieWithComments getMovieInformationWithCommentsByMovieId(String movieId) {
		Movie movie=getMovieInformationById(movieId);
		List<Comment> listOfComments=movieDao.getMovieCommentsByMovieId(movieId);
		MovieWithComments movieInformation=new MovieWithComments(movie, listOfComments);
		movieInformation.setGenres(movieDao.getGenresOfMovieById(movieId));
		movieInformation.setActorsInvolved(movieDao.getActorsOfMovieByMovieId(movieId));
		movieInformation.setRating(movieDao.getRatingByUser(movieId));
		return movieInformation;
	}

	@Override
	public boolean checkIfMovieIdisValid(String movieId) {
		return movieDao.checkIfMovieIdisValid(movieId);
	}

	@Override
	public void insertCommentsAndRatings(String userId, String movieId, String comment, String rating) {
		movieDao.insertCommentsAndRatings(userId, movieId, comment, rating);
		
	}

	@Override
	public void insertMovie(Movie movie) {
		Integer movieId=movieDao.insertMovie(movie);
		String[] genres=movie.getGenreString().split(",");
		for(String genreName:genres) {
			Integer genreId=movieDao.getGenreIdIfPresent(genreName);
			if(genreId==null) {
				genreId=movieDao.insertNewGenre(genreName);
			}
			movieDao.insertMovieGenres(String.valueOf(movieId),String.valueOf(genreId));
		}
	  String[] actors=movie.getActorString().split(",");	
		for(String actorName:actors) {
			Integer actorId=movieDao.getActorIdIfPresent(actorName);
			if(actorId==null) {
				actorId=movieDao.inserNewActor(actorName);
			}
			movieDao.insertMovieActors(String.valueOf(movieId),String.valueOf(actorId));
		}
		
	}

	@Override
	public void deleteMovie(String movieId) {
		movieDao.deleteMovie(movieId);
		
	}

	@Override
	public List<Movie> fetchAllMovies() {
		List<Movie> listOfMovies=movieDao.getListOfMoviesByReleaseRange(new LocalDate(1900,1,1).toDate(), new Date());
		return listOfMovies;
	}

	@Override
	public void updateMovie(Movie movie, String movieId) {
		movieDao.updateMovie(movie, movieId);
		movieDao.deleteAssociationsOfAMovieWithAGenre(movieId);
		movieDao.deleteAssociationsOfAMovieWithAnActor(movieId);
		String[] genres=movie.getGenreString().split(",");
		for(String genreName:genres) {
			Integer genreId=movieDao.getGenreIdIfPresent(genreName);
			if(genreId==null) {
				genreId=movieDao.insertNewGenre(genreName);
			}
			movieDao.insertMovieGenres(String.valueOf(movieId),String.valueOf(genreId));
		}
		String[] actors=movie.getActorString().split(",");
		for(String actorName:actors) {
			Integer actorId=movieDao.getActorIdIfPresent(actorName);
			if(actorId==null) {
				actorId=movieDao.inserNewActor(actorName);
			}
			movieDao.insertMovieActors(String.valueOf(movieId),String.valueOf(actorId));
		}
	}

	@Override
	public List<Movie> getListOfTopRatedMovies() {
		return movieDao.getListOfTopRatedMovies();
	}

	@Override
	public List<Integer> getDistinctUsersWhoHaveCommented() {
		return movieDao.getDistinctUsersWhoHaveCommented();
	}

	@Override
	public Map<Integer, Integer> getMappingOfMoviesCommentedWithRatingsForUser(String userId) {
		return movieDao.getMappingOfMoviesCommentedWithRatingsForUser(userId);
	}

	
	public List<Movie> getRecommendedMovies(String username){
		User user=userService.getUserFromUsername(username);
		 if(!recommendationService.checkIfUserIdIsPresent(user.getId())) {
			 return getListOfTopRatedMovies();
		 }
		 else {
			 Integer similarUser=recommendationService.getSimilarUserToUserId(user.getId());
			 Map<Integer,Integer> mapOfUserRatings=getMappingOfMoviesCommentedWithRatingsForUser(String.valueOf(user.getId()));
			 Map<Integer,Integer> mapOfSimilarUserRatings=getMappingOfMoviesCommentedWithRatingsForUser(String.valueOf(similarUser));
			List<Integer> listOfMoviesNotSeenByUserAndHighlyRatedBySimilarUser=new ArrayList<Integer>();
			 for(Integer movieId:mapOfSimilarUserRatings.keySet()) {
					if(mapOfUserRatings.get(movieId)==null && mapOfSimilarUserRatings.get(movieId)>=3) {
						listOfMoviesNotSeenByUserAndHighlyRatedBySimilarUser.add(movieId);
					}
				}
			 return getMoviesByListOfIds(listOfMoviesNotSeenByUserAndHighlyRatedBySimilarUser);
			 
		 }
	}

	@Override
	public List<Movie> getMoviesByListOfIds(List<Integer> listOfIds) {
		return movieDao.getMoviesByListOfIds(listOfIds);
	}
	
	@Override
	public Movie getMovieForEditInformation(String movieId) {
		Movie movie=getMovieInformationById(movieId);
		List<String> genres=movieDao.getGenresOfMovieById(String.valueOf(movie.getId()));
		List<String> actors=movieDao.getActorsOfMovieByMovieId(String.valueOf(movie.getId()));
		movie.setGenreString(String.join(",", genres));
		movie.setActorString(String.join(",", actors));
		SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
		try {
			movie.setReleaseDateInString(df.format(movie.getReleaseDate()));
		}
		catch(Exception e ) {
			
		}
		return movie;
		
	}
	

	

}
