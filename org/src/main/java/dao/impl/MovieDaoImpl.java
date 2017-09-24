package dao.impl;

import java.util.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dao.IMovieDao;
import model.Movie;

@Repository("MovieDaoImpl")
public class MovieDaoImpl implements IMovieDao {
	
	@Autowired
	private DataSource datasource;

	private JdbcTemplate jdbcTemplate;
	
	
	
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}



	@Override
	public List<Movie> getListOfMoviesByGenre(String genre) {
		String sql="Select A.id,A.lang,A.description,A.poster,A.rated,A.title,A.runtime,A.releaseDate"
				+ " from movie AS A, movie_genre AS B, genre As G where A.id=B.movie_id and B.genre_id=G.id and G.name=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {genre});
		return getListOfMoviesFromRows(rows);
	}
	
	
	private List<Movie> getListOfMoviesFromRows(List<Map<String,Object>> rows){
		List<Movie> listOfMovies=new ArrayList<Movie>();
		for(Map<String,Object> row:rows) {
			Movie movie=new Movie();
			movie.setId((Integer)row.get("id"));
			movie.setLanguage((String)row.get("lang"));
			movie.setDescription((String)row.get("description"));
			movie.setPoster((String)row.get("poster"));
			movie.setRated((String)row.get("rated"));
			movie.setTitle((String)row.get("title"));
			movie.setRuntime((String) row.get("runtime"));
			movie.setReleaseDate((Date)row.get("releaseDate"));
			listOfMovies.add(movie);
		}
		return listOfMovies;
	}



	@Override
	public List<Movie> getListOfMoviesByLanguage(String language) {
		String sql="Select * from movie where lang=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {language});
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public List<Movie> getListOfMoviesByReleaseRange(Date startDate, Date endDate) {
		String sql="Select * from movie where releaseDate between ? and ?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {startDate,endDate});
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public List<Movie> getListOfMoviesBySearchTitle(String title) {
		String sql="Select * from movie where title LIKE ?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		title="%"+title+"%";
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {title});
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public List<Movie> getListOfMoviesBySearchArtist(String actor) {
		String sql="Select A.id,A.lang,A.description,A.poster,A.rated,A.title,A.runtime,A.releaseDate"
				+ " from movie AS A, movie_actor AS B, actor As G where A.id=B.movie_id and B.actor_id=G.id and G.name LIKE ?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		actor="%"+actor+"%";
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {actor});
		return getListOfMoviesFromRows(rows);
	}
	

}
