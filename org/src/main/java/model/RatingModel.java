package model;

public class RatingModel {

	Integer ratingOfUser1;
	
	
	Integer ratingOfUser2;
	
	public RatingModel() {
		
	}

	public RatingModel(Integer ratingOfUser1, Integer ratingOfUser2) {
		this.ratingOfUser1 = ratingOfUser1;
		this.ratingOfUser2 = ratingOfUser2;
	}

	public Integer getRatingOfUser1() {
		return ratingOfUser1;
	}

	public void setRatingOfUser1(Integer ratingOfUser1) {
		this.ratingOfUser1 = ratingOfUser1;
	}

	public Integer getRatingOfUser2() {
		return ratingOfUser2;
	}

	public void setRatingOfUser2(Integer ratingOfUser2) {
		this.ratingOfUser2 = ratingOfUser2;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RatingModel [ratingOfUser1=").append(ratingOfUser1).append(", ratingOfUser2=")
				.append(ratingOfUser2).append("]");
		return builder.toString();
	}
	
	
}
