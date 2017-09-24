package dao;

import java.util.Date;
import java.util.List;

import model.Movie;

public interface IMovieDao {
	
	public List<Movie> getListOfMoviesByGenre(String genre);
	
	public List<Movie> getListOfMoviesByLanguage(String language);
	
	public List<Movie> getListOfMoviesByReleaseRange(Date startDate,Date endDate);
	
	public List<Movie> getListOfMoviesBySearchTitle(String title);
	
	public List<Movie> getListOfMoviesBySearchArtist(String actor);
	 

}
