package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.InsertRequest;
import model.Movie;
import model.MovieWithComments;
import model.ResponseMessage;
import service.IMovieService;
import service.IUserService;

@Controller
@RequestMapping("/movieInformation")
public class MovieInformationController {
	
	@Autowired
	IMovieService movieService;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/{username}/{movieId}",method=RequestMethod.GET)
	public ModelAndView getView(@PathVariable String username,@PathVariable String movieId) {
			ModelAndView modelView=new ModelAndView("movieinformation");
			modelView.addObject("username",username);
			modelView.addObject("movieId",movieId);
			return modelView;
	}
	
	
	@RequestMapping(value="/getInformation/{movieId}",method=RequestMethod.GET)
	public @ResponseBody Movie getMovieInformationById(@PathVariable String movieId) {
		   return movieService.getMovieInformationById(movieId);
	}
	
	@RequestMapping(value="/getMovieInformationWithComments/{movieId}",method=RequestMethod.GET)
	public @ResponseBody MovieWithComments getMovieInformationWithComments(@PathVariable String movieId) {
		  MovieWithComments movieInformationWithComments=null;
		  try {
			  movieInformationWithComments=movieService.getMovieInformationWithCommentsByMovieId(movieId);
		  }
		  catch(Exception e) {
			  System.out.println(e.getStackTrace());
		  }
		  return movieInformationWithComments;
	}
	
	@RequestMapping(value="/insertComments",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody MovieWithComments insertComments(@RequestBody InsertRequest request) {
		MovieWithComments movieInformationWithComments=null;
		try {
			if(!movieService.checkIfMovieIdisValid(request.getMovieId())) {
				movieInformationWithComments.setStatusCode(404);
				movieInformationWithComments.setStatusMessage("The Movie for which the comment should be set not found");
			}
			else if(!userService.validateUserName(request.getUsername())) {
				movieInformationWithComments.setStatusCode(404);
				movieInformationWithComments.setStatusMessage("The UserName for which the comment should be set not found");
			}
			else {
				Integer userId=userService.getUserIdFromUsername(request.getUsername());
				movieService.insertCommentsAndRatings(String.valueOf(userId), request.getMovieId(), request.getComment(),request.getRating());
				movieInformationWithComments=movieService.getMovieInformationWithCommentsByMovieId(request.getMovieId());
			}
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return movieInformationWithComments;
	}
	
	@RequestMapping(value="/insertMovie",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody ResponseMessage insertMovie(@RequestBody Movie movie) {
		ResponseMessage response=null;
		try {
			SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
			movie.setReleaseDate(df.parse(movie.getReleaseDateInString()));
			movieService.insertMovie(movie);
			response=new ResponseMessage("200","Movie inserted");
		}
		catch(Exception e) {
			response=new ResponseMessage("500","Exception");
		}
		return response;
	}
	
	@RequestMapping(value="/deleteMovie",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody ResponseMessage deleteMovie(@RequestBody Movie movie) {
		ResponseMessage response=null;
		try {
			movieService.deleteMovie(String.valueOf(movie.getId()));;
			response=new ResponseMessage("200","Movie inserted");
		}
		catch(Exception e) {
			response=new ResponseMessage("500","Exception");
		}
		return response;
	}
	
	@RequestMapping(value="/updateMovie",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody ResponseMessage updateMovie(@RequestBody Movie movie) {
		ResponseMessage response=null;
		try {
			SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");
			movie.setReleaseDate(df.parse(movie.getReleaseDateInString()));
			movieService.updateMovie(movie,String.valueOf(movie.getId()));
			response=new ResponseMessage("200","Movie inserted");
		}
		catch(Exception e) {
			response=new ResponseMessage("500","Exception");
		}
		return response;
	}
	
	@RequestMapping(value="/fetchAllMovies",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody List<Movie> fetchAllMovies() {
		List<Movie> listOfMovies=new ArrayList<Movie>();
		try {
			listOfMovies=movieService.fetchAllMovies();
		}
		catch(Exception e) {

		}
		return listOfMovies;
	}
}

