package dao.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.sql.DataSource;
import model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
				+ " from movie AS A, movie_genre AS B, genre As G where A.id=B.movie_id and B.genre_id=G.id and A.deleted=false and G.name=?";
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
			SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
			try {
				movie.setReleaseDateInString(df.format(movie.getReleaseDate()));
			}
			catch(Exception e ) {
				
			}
			listOfMovies.add(movie);
		}
		return listOfMovies;
	}



	@Override
	public List<Movie> getListOfMoviesByLanguage(String language) {
		String sql="Select * from movie where deleted=false and  lang=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {language});
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public List<Movie> getListOfMoviesByReleaseRange(Date startDate, Date endDate) {
		String sql="Select * from movie where deleted=false and  releaseDate between ? and ?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {startDate,endDate});
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public List<Movie> getListOfMoviesBySearchTitle(String title) {
		String sql="Select * from movie where deleted=false and  title LIKE ?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		title="%"+title+"%";
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {title});
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public List<Movie> getListOfMoviesBySearchArtist(String actor) {
		String sql="Select A.id,A.lang,A.description,A.poster,A.rated,A.title,A.runtime,A.releaseDate"
				+ " from movie AS A, movie_actor AS B, actor As G where A.id=B.movie_id and B.actor_id=G.id and A.deleted=false and G.name LIKE ?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		actor="%"+actor+"%";
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {actor});
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public Movie getMovieInformationById(String id) {
		String sql="Select * from movie where deleted=false and id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> listOfRows=template.queryForList(sql,new Object[] {id});
		Movie movie=new Movie();
	    if(listOfRows.size()>0) {
	    	Map<String,Object> row=listOfRows.get(0);
	    	movie.setId((Integer)row.get("id"));
			movie.setLanguage((String)row.get("lang"));
			movie.setDescription((String)row.get("description"));
			movie.setPoster((String)row.get("poster"));
			movie.setRated((String)row.get("rated"));
			movie.setTitle((String)row.get("title"));
			movie.setRuntime((String) row.get("runtime"));
			movie.setReleaseDate((Date)row.get("releaseDate"));
	    }
	    return movie;
	}



	@Override
	public List<Comment> getMovieCommentsByMovieId(String movieId) {
		String sql="Select users.username,movie_ratings.comment,movie_ratings.rating "
				+ " from movie_ratings,users where movie_ratings.user_id=users.id and movie_ratings.movie_id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {movieId});
		List<Comment> listOfComments=new ArrayList<Comment>();
		for(Map<String,Object> row:rows) {
			Comment comment=new Comment();
			comment.setUsername((String)row.get("username"));
			comment.setComment((String)row.get("comment"));
			comment.setRating((Integer)row.get("rating"));
			listOfComments.add(comment);
		}
		return listOfComments;
	}



	@Override
	public List<String> getGenresOfMovieById(String movieId) {
		String sql="Select genre.name from genre,movie_genre where genre.id=movie_genre.genre_id and movie_genre.movie_id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {movieId});
		List<String> genreList=new ArrayList<String>();
		for(Map<String,Object>row:rows) {
			genreList.add((String)row.get("name"));
		}
		return genreList;
	}



	@Override
	public List<String> getActorsOfMovieByMovieId(String movieId) {
		String sql="Select actor.name from actor,movie_actor where actor.id=movie_actor.actor_id and movie_actor.movie_id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,new Object[] {movieId});
		List<String> actorList=new ArrayList<String>();
		for(Map<String,Object>row:rows) {
			actorList.add((String)row.get("name"));
		}
		return actorList;
	}



	@Override
	public void insertCommentsAndRatings(String userId, String movieId, String comment, String rating) {
		String sql="Insert into movie_ratings(user_id,movie_id,comment,rating) VALUES(?,?,?,?)";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {userId,movieId,comment,rating});
	}



	@Override
	public boolean checkIfMovieIdisValid(String movieId) {
		String sql="Select count(*) from movie where deleted=false and id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		Integer rowCount=template.queryForObject(sql,new Object[] {movieId},Integer.class);
		if(rowCount==1) {
			return true;
		}
		return false;
	}



	@Override
	public int insertMovie(Movie movie) {
		String sql="Insert into movie(title,rated,runtime,lang,poster,description,releaseDate) values(?,?,?,?,?,?,?)";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {movie.getTitle(),movie.getRated(),movie.getRuntime(),movie.getLanguage()
				,movie.getPoster(),movie.getDescription(),movie.getReleaseDate()});
		sql="Select id from movie order by id DESC limit 1";
		Integer idInserted=template.queryForObject(sql,Integer.class);
		return idInserted;
	}



	@Override
	public void deleteMovie(String movieId) {
		String sql="Update movie set deleted=true where id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {movieId});
		
	}



	@Override
	public List<Movie> fetchAllMovies() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void updateMovie(Movie movie, String movieId) {
		String sql="Update movie set title=?, rated=?, releaseDate=?, runtime=?, lang=?, poster=?, description=? where id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {movie.getTitle(),movie.getRated(),movie.getReleaseDate(),movie.getRuntime(),movie.getLanguage()
				,movie.getPoster(),movie.getDescription(),movie.getId()});		
	}



	@Override
	public void insertMovieGenres(String movieId,String genreId) {
		String sql="Insert into movie_genre(movie_id,genre_id) VALUES (?,?)";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {movieId,genreId});
	}



	@Override
	public void insertMovieActors(String movieId,String actorId) {
		String sql="Insert into movie_actor(movie_id,actor_id) VALUES (?,?)";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {movieId,actorId});
	}



	@Override
	public Integer getGenreIdIfPresent(String genreName) {
		Integer genreId=null;
		String sql="Select id from genre where name LIKE ? order by id DESC limit 1";
		genreName="%"+genreName+"%";
		JdbcTemplate template=new JdbcTemplate(datasource);
		genreId=template.queryForObject(sql,new Object[] {genreName},Integer.class);
		return genreId;
		
	}



	@Override
	public Integer getActorIdIfPresent(String actorName) {
		Integer actorId=null;
		String sql="Select id  from actor where name LIKE ? order by id DESC limit 1";
		actorName="%"+actorName+"%";
		JdbcTemplate template=new JdbcTemplate(datasource);
		actorId=template.queryForObject(sql,new Object[] {actorName},Integer.class);
		return actorId;		
	}



	@Override
	public Integer insertNewGenre(String genreName) {
		String sql="Insert into genre(name) VALUES(?)";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {genreName});
		sql="Select id from genre order by id DESC limit 1";
		Integer idInserted=template.queryForObject(sql,Integer.class);
		return idInserted;
		
	}



	@Override
	public Integer inserNewActor(String actorName) {
		String sql="Insert into actor(name) VALUES(?)";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {actorName});
		sql="Select id from actor order by id DESC limit 1";
		Integer idInserted=template.queryForObject(sql,Integer.class);
		return idInserted;
		
	}



	@Override
	public void deleteAssociationsOfAMovieWithAGenre(String movieId) {
		String sql="Delete from movie_genre where movie_id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {movieId});
	}



	@Override
	public void deleteAssociationsOfAMovieWithAnActor(String movieId) {
		String sql="Delete from movie_actor where movie_id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		template.update(sql,new Object[] {movieId});
	}



	@Override
	public List<Movie> getListOfTopRatedMovies() {
		String sql="SELECT * from movie as a join (" + 
				"Select AVG(rating)as average_rating,movie_id from movie_ratings group by movie_id) as b on b.movie_id=a.id order by b.average_rating DESC, b.movie_id DESC";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql);
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
	public List<Integer> getDistinctUsersWhoHaveCommented() {
		List<Integer> listOfUserIds=new ArrayList<Integer>();
		String sql="Select DISTINCT(user_id) from movie_ratings";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql);
		for(Map<String,Object>row:rows) {
			listOfUserIds.add((Integer)row.get("user_id"));
		}
		return listOfUserIds;
	}



	@Override
	public Map<Integer, Integer> getMappingOfMoviesCommentedWithRatingsForUser(String userId) {
		Map<Integer,Integer> mapOfMoviesCommentedWithRatingsForUsers=new HashMap<Integer,Integer>();
		String sql="Select movie_id,rating from movie_ratings where user_id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		List<Map<String,Object>> rows=template.queryForList(sql,userId);
		for(Map<String,Object>row:rows) {
			mapOfMoviesCommentedWithRatingsForUsers.put((Integer)row.get("movie_id"),(Integer)row.get("rating"));
		}
		return mapOfMoviesCommentedWithRatingsForUsers;
	}



	@Override
	public List<Movie> getMoviesByListOfIds(List<Integer> listOfIds) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
		String sql="Select * from movie where id IN ( :ids)";
		Map idsMap = Collections.singletonMap("ids", listOfIds);
		List<Map<String,Object>> rows=jdbcTemplate.queryForList(sql,idsMap);
		return getListOfMoviesFromRows(rows);
	}



	@Override
	public Integer getRatingByUser(String movieId) {
		Integer rating=0;
		String sql="Select avg(rating) from movie_ratings where movie_id=?";
		JdbcTemplate template=new JdbcTemplate(datasource);
		rating=template.queryForObject(sql,new Object[] {movieId},Integer.class);
		return rating;
	}



	
	
	
	

}
