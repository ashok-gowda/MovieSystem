package model;
import java.util.*;

public class MovieWithComments {
	
	private Movie movie;
	
	private List<Comment> listOfComments;
	
	private List<String> genres;
	
	private List<String> actorsInvolved;
	
	private int statusCode;
	
	private int rating;
	
	String statusMessage;

	public MovieWithComments(Movie movie, List<Comment> listOfComments) {
		super();
		this.movie = movie;
		this.listOfComments = listOfComments;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}
	
	
	

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getActorsInvolved() {
		return actorsInvolved;
	}

	public void setActorsInvolved(List<String> actorsInvolved) {
		this.actorsInvolved = actorsInvolved;
	}
	
	

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovieWithComments [movie=").append(movie).append(", listOfComments=").append(listOfComments)
				.append(", genres=").append(genres).append(", actorsInvolved=").append(actorsInvolved).append("]");
		return builder.toString();
	}


}
