package model;

import java.util.Date;

public class Movie {

	Integer id;
	
	String title;
	
	String rated;
	
	Date releaseDate;
	
	String runtime;
	
	String language;
	
	String poster;
	
	String description;
	
	public Movie() {
		
	}

	public Movie(Integer id, String title, String rated, Date releaseDate, String runtime, String language, String poster,
			String description) {
		super();
		this.id = id;
		this.title = title;
		this.rated = rated;
		this.releaseDate = releaseDate;
		this.runtime = runtime;
		this.language = language;
		this.poster = poster;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Movie [id=").append(id).append(", title=").append(title).append(", rated=").append(rated)
				.append(", releaseDate=").append(releaseDate).append(", runtime=").append(runtime).append(", language=")
				.append(language).append(", poster=").append(poster).append(", description=").append(description)
				.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
