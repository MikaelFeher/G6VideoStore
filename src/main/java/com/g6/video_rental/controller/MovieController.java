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

    @Autowired
    private MovieRepository movieRepository;

//    Needs a redirect to the "/" getmapping when the url is 'http://localhost/movies'...

    @GetMapping("/")
    public String getMovies(Model model) {
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies/movies";
    }
    @PostMapping("/")
    public String findMovies(@ModelAttribute Movie movie, Model model) {
        List<Movie> allMovies = (List<Movie>) movieRepository.findAll();
        List<Movie> filteredMovies = new ArrayList<>();
        List<Movie> tempMovies = new ArrayList<>();

        for (Movie m : allMovies){
            if (movie.getName() != null) {
                tempMovies = movieRepository.findByName(movie.getName());
                for (int i = 0; i < filteredMovies.size(); i++) {
                    for (int j = 0; j < tempMovies.size(); j++) {
                        if (tempMovies.get(j).getProductNumber() != filteredMovies.get(i).getProductNumber() ) {
                            filteredMovies.add(tempMovies.get(j));
                        }
                    }
                }
            }
//            if (movie.getCategory() != null) {
//                tempMovies = movieRepository.findByCategory(category);
//                for (int i = 0; i < filteredMovies.size(); i++) {
//                    for (int j = 0; j < tempMovies.size(); j++) {
//                        if (tempMovies.get(j).getProductNumber() != filteredMovies.get(i).getProductNumber() ) {
//                            filteredMovies.add(tempMovies.get(j));
//                        }
//                    }
//                }
//            }
//            if (releaseYear != null) {
//                tempMovies = movieRepository.findByReleaseYear(releaseYear);
//                for (int i = 0; i < filteredMovies.size(); i++) {
//                    for (int j = 0; j < tempMovies.size(); j++) {
//                        if (tempMovies.get(j).getProductNumber() != filteredMovies.get(i).getProductNumber() ) {
//                            filteredMovies.add(tempMovies.get(j));
//                        }
//                    }
//                }
//            }
        }

        model.addAttribute("movies", filteredMovies);
        return "movies/movies";
    }

    @GetMapping("/movie/{productNumber}")
    public String getMovieByProductNumber(Model model, @PathVariable Long productNumber) {
        List<Movie> singleMovie = movieRepository.findByProductNumber(productNumber);
        model.addAttribute("singleMovie", singleMovie);
        return "movies/movie";
    }
}
