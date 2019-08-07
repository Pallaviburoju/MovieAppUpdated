package com.capgemini.moviecatalogservice.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.moviecatalogservice.model.CatalogItem;
import com.capgemini.moviecatalogservice.model.Movie;
import com.capgemini.moviecatalogservice.model.MovieCatalog;
import com.capgemini.moviecatalogservice.model.Rating;
import com.capgemini.moviecatalogservice.model.UserRating;
import com.capgemini.moviecatalogservice.service.MovieInfoService;
import com.capgemini.moviecatalogservice.service.MovieRatingService;
@RestController
@RequestMapping("/catalog")
//@CrossOrigin("http://localhost:4200")
public class MovieCatalogController {

//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired private MovieInfoService movieInfoService;
	@Autowired private MovieRatingService movieRatingService;
	
	
	@GetMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "fallBackGetMovieCatalog")
	public MovieCatalog getMovieCatalog(@PathVariable String userId)
	{
		//call movie-rating-service and get the rating details
		UserRating ratings = movieRatingService.getUserRating(userId);
		
		List<Rating> movieRatings=ratings.getRatings();
		List<CatalogItem> catalogItems=new ArrayList<CatalogItem>();
		
		//call movie-service and get movie details
	    for(Rating movieRating:movieRatings)
	    {
	    	Movie movie = movieInfoService.getMovieInfo(movieRating);
	    	 
	          catalogItems.add(new CatalogItem(movie,movieRating.getRating())); 	
	    }
		
		//We have to return MovieCatalog to client
	    MovieCatalog movieCatalog=new MovieCatalog(catalogItems);
	    return movieCatalog;
	
	}

}
