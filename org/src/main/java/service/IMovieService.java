package service;

import java.util.Date;
import java.util.List;


import model.Movie;
import model.MovieWithComments;

public interface IMovieService {
	
	
	public List<Movie> getListOfMoviesByGenre(String genre);
	
	public List<Movie> getListOfMoviesByLanguage(String language);
	
	public List<Movie> getListOfMoviesByReleaseRange(String category);
	
	public List<Movie> getListOfMoviesBySearchTerm(String term);
	
	public Movie getMovieInformationById(String id);
	
	public MovieWithComments getMovieInformationWithCommentsByMovieId(String movieId);
	
	public boolean checkIfMovieIdisValid(String movieId);
	
	public void insertCommentsAndRatings(String userId, String movieId, String comment, String rating);
	
	public void insertMovie(Movie movie);
	
	public void deleteMovie(String movieId);
	
	public List<Movie> fetchAllMovies();
	
	public void updateMovie(Movie movie,String movieId);

}
