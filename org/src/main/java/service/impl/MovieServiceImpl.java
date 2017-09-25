package service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IMovieDao;
import model.Comment;
import model.Movie;
import model.MovieWithComments;
import service.IMovieService;

@Service("MovieServiceImpl")
public class MovieServiceImpl implements IMovieService {
	
	@Autowired
	IMovieDao movieDao;

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
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
		switch(category) {
		case "1970-1980":return movieDao.getListOfMoviesByReleaseRange(new LocalDate(1970,1,1).toDate(), new LocalDate(1980,12,31).toDate());
		case "1980-1990":return movieDao.getListOfMoviesByReleaseRange(new LocalDate(1981,1,1).toDate(), new LocalDate(1990,12,31).toDate());
		case "1990-2000":return movieDao.getListOfMoviesByReleaseRange(new LocalDate(1991,1,1).toDate(), new LocalDate(2000,12,31).toDate());
		case "2000-2010":return movieDao.getListOfMoviesByReleaseRange(new LocalDate(2001,1,1).toDate(), new LocalDate(2010,12,31).toDate());
		case "After2010":return movieDao.getListOfMoviesByReleaseRange(new LocalDate(2011,1,1).toDate(), new Date());
		}
		return null;
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

	

	
	
	
	

	

}
