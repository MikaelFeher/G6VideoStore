package com.g6.video_rental.controller;

import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("")
    public String getMovies(Model model) {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies/movies";
    }

    @GetMapping("/searchMovies")
    public String search(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) String category, @RequestParam(required = false) String releaseYear) {
        List<Movie> filteredMovies = movieRepository.findByNameContainsIgnoreCaseAndCategoryContainsIgnoreCaseAndReleaseYearContains(name, category, releaseYear);
        model.addAttribute("movies", filteredMovies);
        return "movies/movies";
    }

    @GetMapping("/movie/{productNumber}/details")
    public String getMovieByProductNumber(Model model, @PathVariable Long productNumber) {
        List<Movie> singleMovie = movieRepository.findByProductNumber(productNumber);
        model.addAttribute("singleMovie", singleMovie);
        return "movies/details";
    }
}
