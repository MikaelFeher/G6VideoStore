package com.g6.video_rental.Controllers.Movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("movie")
public class MoviesController {
    @GetMapping("/movies")
    public String getMovies() {
        return "movie/movies";
    }
}
