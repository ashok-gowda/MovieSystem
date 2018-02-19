package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.Movie;
import service.IMovieService;

@Controller
@RequestMapping("/movieIndex")
public class MovieIndexController {
	
	@Autowired
	IMovieService movieService;
	
	@RequestMapping(value="/{username}", method = RequestMethod.GET)
	public ModelAndView getMovieIndex(@PathVariable String username) {
		ModelAndView modelView=new ModelAndView("movieindex");
		modelView.addObject("username",username);
		return modelView;
	}
	
	@RequestMapping(value="/find/genre/{genre}",method=RequestMethod.GET)
	public @ResponseBody List<Movie> getMoviesByGenre(@PathVariable String genre){
		List<Movie> listOfMovies=null;
		try {
		listOfMovies=movieService.getListOfMoviesByGenre(genre);
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return listOfMovies;
	}
	
	@RequestMapping(value="/find/language/{language}",method=RequestMethod.GET)
	public @ResponseBody List<Movie> getListOfMoviesByLanguage(@PathVariable String language){
		List<Movie> listOfMovies=null;
		try {
			listOfMovies=movieService.getListOfMoviesByLanguage(language);
			}
			catch(Exception e) {
				System.out.println(e.getStackTrace());
			}
			return listOfMovies;
	}
	
	@RequestMapping(value="/find/yearRange/{category}",method=RequestMethod.GET)
	public @ResponseBody List<Movie> getListOfMoviesByReleaseRange(@PathVariable String category){
		List<Movie> listOfMovies=null;
		try {
			listOfMovies=movieService.getListOfMoviesByReleaseRange(category);
			}
			catch(Exception e) {
				System.out.println(e.getStackTrace());
			}
			return listOfMovies;
	}
	
	@RequestMapping(value="/search/{searchTerm}",method=RequestMethod.GET)
	public @ResponseBody List<Movie> getListOfMoviesBySearchTerm(@PathVariable String searchTerm){
		List<Movie> listOfMovies=null;
		try {
			listOfMovies=movieService.getListOfMoviesBySearchTerm(searchTerm);
			}
			catch(Exception e) {
				System.out.println(e.getStackTrace());
			}
			return listOfMovies;
	}
	
	@RequestMapping(value="/getTopRecommendedMovies",method=RequestMethod.GET)
	public @ResponseBody List<Movie> getTopRecommendedMovies(){
		List<Movie> listOfMovies=null;
		try {
			listOfMovies=movieService.getListOfTopRatedMovies();
		}
		catch(Exception e) {
			
		}
		return listOfMovies;
	}
	
	
	@RequestMapping(value="/getRecommendedMovies/{username}",method=RequestMethod.GET)
	public @ResponseBody List<Movie> getRecommendededMovies(@PathVariable String username){
		List<Movie> listOfMovies=null;
		try {
			listOfMovies=movieService.getRecommendedMovies(username);
		}
		catch(Exception e) {
		}
		return listOfMovies;
	}

}
