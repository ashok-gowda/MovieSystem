package dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	public int insertMovie(Movie movie);
	
	public void insertMovieGenres(String movieId, String genreId);
	
	public void insertMovieActors(String movieId,String actorId);
	
	public void deleteMovie(String movieId);
	
	public List<Movie> fetchAllMovies();
	
	public void updateMovie(Movie movie,String movieId);
	
	public Integer getGenreIdIfPresent(String genreName);
	
	public Integer getActorIdIfPresent(String actorName);
	
	public Integer insertNewGenre(String genreName);
	
	public Integer inserNewActor(String actorName);
	
	public void deleteAssociationsOfAMovieWithAGenre(String movieId);
	
	public void deleteAssociationsOfAMovieWithAnActor(String actorId);
	
	public List<Movie> getListOfTopRatedMovies();
	
	public List<Integer> getDistinctUsersWhoHaveCommented();
	
	public Map<Integer,Integer> getMappingOfMoviesCommentedWithRatingsForUser(String userId);
	
	public List<Movie> getMoviesByListOfIds(List<Integer> listOfIds);

}
