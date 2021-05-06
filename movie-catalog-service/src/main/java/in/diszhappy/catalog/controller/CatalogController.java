package in.diszhappy.catalog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import in.diszhappy.catalog.model.CatalogItem;
import in.diszhappy.catalog.model.Movie;
import in.diszhappy.catalog.model.Rating;

@RestController
@RequestMapping(value = "/catalog")
public class CatalogController {
	
	@Autowired
	private RestTemplate template;
	@Autowired
	private WebClient.Builder builder;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		 List<CatalogItem>  ret =  new ArrayList<>();
		
		
		List<Rating> ratings =
				Arrays.asList(new Rating("1", 1),
						new Rating("2", 2),
						new Rating("3", 3),
						new Rating("4", 4),
						new Rating("5", 5)); 
		System.out.println(ratings);
		
		
		
	
		
		return ratings.stream().map(rating ->{
					//Movie m = template.getForObject("http://localhost:8083/movies/"+rating.getMovieId(), Movie.class);
			Movie m = builder.build().get().uri("http://localhost:8083/movies/\"+rating.getMovieId()")
			.retrieve()
			.bodyToMono(Movie.class)
			.block();
			
			return new CatalogItem(m.getName(),"Test Desc",rating.getRating());
		}).collect(Collectors.toList());
		
//		return ret;
	}
}
