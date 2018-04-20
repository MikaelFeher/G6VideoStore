package com.g6.video_rental.controller;

import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private String MOVIES_MAIN = "/movies";

    @Autowired
    private MovieRepository movieRepository;

    // List all movies...
    @GetMapping("")
    public String getMovies(Model model) {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies/movies";
    }

    // Search for movies by name, category and/or release year...
    @GetMapping("/searchMovies")
    public String search(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) String category, @RequestParam(required = false) String releaseYear) {
        List<Movie> filteredMovies = movieRepository.findByNameContainsIgnoreCaseAndCategoryContainsIgnoreCaseAndReleaseYearContains(name, category, releaseYear);
        model.addAttribute("movies", filteredMovies);
        return "movies/movies";
    }

    // Show details of a specific movie...
    @GetMapping("/movie/{productNumber}/details")
    public String showMovieDetails(Model model, @PathVariable Long productNumber) {
        model.addAttribute("singleMovie", movieRepository.findByProductNumber(productNumber));
        return "movies/details";
    }

    // Add a new movie...
    @GetMapping("/addmovie")
    public String addMovie(Model model) {
        model.addAttribute("newMovie", new Movie());
        return "movies/addmovie";
    }

    @PostMapping("/addmovie")
    public String addMovie(@ModelAttribute("newMovie") Movie newMovie) {
        System.out.println("New movie added, name: " + newMovie.getName());
        movieRepository.save(newMovie);
        return "redirect:" + MOVIES_MAIN;
    }

    // Edit movie details...
    @GetMapping("/movie/{productNumber}/edit")
    public String editMovieDetails(Model model, @PathVariable Long productNumber) {
        model.addAttribute("movieToEdit", movieRepository.findByProductNumber(productNumber));
        return "movies/edit";
    }

    @PostMapping("/movie/{productNumber}/edit")
    public String editMovieDetails(@ModelAttribute("movieToEdit") Movie movieToEdit) {
        movieRepository.save(movieToEdit);
        return "redirect:" + MOVIES_MAIN;
    }

    // Delete selected movie
    @GetMapping("/movie/{productNumber}/delete")
    public String deleteSelectedMovie(Model model, @PathVariable Long productNumber) {
        model.addAttribute("movieToDelete", movieRepository.findByProductNumber(productNumber));
        return "movies/delete";
    }

    @DeleteMapping("/movie/delete")
    public String deleteSelectedMovie(@ModelAttribute("movieToDelete") Movie movieToDelete) {
        movieRepository.deleteById(movieToDelete.getProductNumber());
        return "redirect:" + MOVIES_MAIN;
    }
}
