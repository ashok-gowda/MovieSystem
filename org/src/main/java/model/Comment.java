package model;

public class Comment {
	
	private String username;
	
	private String comment;
	
	private Integer rating;
	
	public Comment() {
		
	}

	public Comment(String username, String comment, Integer rating) {
		super();
		this.username = username;
		this.comment = comment;
		this.rating = rating;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [username=").append(username).append(", comment=").append(comment).append(", rating=")
				.append(rating).append("]");
		return builder.toString();
	}

}
