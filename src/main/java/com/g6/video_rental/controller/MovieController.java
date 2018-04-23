package com.g6.video_rental.controller;

import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.repository.MovieRepository;
import com.g6.video_rental.domain.repository.RentedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private String MOVIES_MAIN = "/movies";

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private RentedMovieRepository rentedMovieRepository;

    // List all movies...
    @GetMapping("")
    public String getMovies(Model model) {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("title", "Filmer");
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
        Movie singleMovie = movieRepository.findByProductNumber(productNumber);
        model.addAttribute("singleMovie", singleMovie);
        model.addAttribute("title", "Info | " + singleMovie.getName());
        return "movies/details";
    }

    // Add a new movie...
    @GetMapping("/addmovie")
    public String addMovie(Model model) {
        model.addAttribute("newMovie", new Movie());
        model.addAttribute("title", "Lägg till ny film");
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
        Movie movieToEdit = movieRepository.findByProductNumber(productNumber);
        model.addAttribute("movieToEdit", movieToEdit);
        model.addAttribute("title", "Redigera | " + movieToEdit.getName());
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
        Movie movieToDelete = movieRepository.findByProductNumber(productNumber);
        String deleteWarning = "Vill du verkligen RADERA " + movieToDelete.getName() + "?\nÅtgärden går ej att återställa!";
        model.addAttribute("movieToDelete", movieToDelete);
        model.addAttribute("title", "Radera | " + movieToDelete.getName());
        model.addAttribute("deleteWarning", deleteWarning);
        return "movies/delete";
    }

    @PostMapping("/movie/{productNumber}/delete")
    public String deleteSelectedMovie(@PathVariable Long productNumber) {
        Movie movieToDelete = movieRepository.findByProductNumber(productNumber);
        movieRepository.deleteById(movieToDelete.getProductNumber());
        return "redirect:" + MOVIES_MAIN;
    }
}
