package model;

public class InsertRequest {
	String movieId;
	
	String username;
	
	String  comment;
	
	String rating;
	
	InsertRequest(){
		
	}

	public InsertRequest(String movieId, String username, String comment, String rating) {
		this.movieId = movieId;
		this.username = username;
		this.comment = comment;
		this.rating = rating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsertRequest [movieId=").append(movieId).append(", username=").append(username)
				.append(", comment=").append(comment).append(", rating=").append(rating).append("]");
		return builder.toString();
	}
	
	

}
