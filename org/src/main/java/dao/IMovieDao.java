package dao;

import java.util.Date;
import java.util.List;

import model.Comment;

import model.Movie;

public interface IMovieDao {
	
	public List<Movie> getListOfMoviesByGenre(String genre);
	
	public List<Movie> getListOfMoviesByLanguage(String language);
	
	public List<Movie> getListOfMoviesByReleaseRange(Date startDate,Date endDate);
	
	public List<Movie> getListOfMoviesBySearchTitle(String title);
	
	public List<Movie> getListOfMoviesBySearchArtist(String actor);
	
	public Movie getMovieInformationById(String id);
	 
	public List<Comment> getMovieCommentsByMovieId(String movieId);
	
	public List<String> getGenresOfMovieById(String movieId);
	
	public List<String> getActorsOfMovieByMovieId(String movieId);
	
	public void insertCommentsAndRatings(String userId,String movieId,String comment,String rating);
	
	public boolean checkIfMovieIdisValid(String movieId);

}
