package service;

import java.util.Date;
import java.util.List;


import model.Movie;

public interface IMovieService {
	
	
	public List<Movie> getListOfMoviesByGenre(String genre);
	
	public List<Movie> getListOfMoviesByLanguage(String language);
	
	public List<Movie> getListOfMoviesByReleaseRange(String category);
	
	public List<Movie> getListOfMoviesBySearchTerm(String term);
	
	

}
