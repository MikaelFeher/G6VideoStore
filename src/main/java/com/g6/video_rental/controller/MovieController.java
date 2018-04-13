package com.g6.video_rental.controller;

import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies/movies";
    }
}
