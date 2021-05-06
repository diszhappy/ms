package in.diszhappy.rating.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.diszhappy.rating.model.Rating;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId, 1);
	}
	@GetMapping("/getAll")
	public List<Rating> getRating() {
		return Arrays.asList(new Rating("1", 1),
				new Rating("2", 2),
				new Rating("3", 3),
				new Rating("4", 4),
				new Rating("5", 5));
	}
}
