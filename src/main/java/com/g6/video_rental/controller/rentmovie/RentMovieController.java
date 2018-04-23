package com.g6.video_rental.controller.rentmovie;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.Entities.RentedMovie;
import com.g6.video_rental.domain.repository.CustomerRepository;
import com.g6.video_rental.domain.repository.MovieRepository;
import com.g6.video_rental.domain.repository.RentedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/rentedmovies")
public class RentMovieController {

    @Autowired
    RentedMovieRepository rentedMovieRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CustomerRepository customerRepository;


    @GetMapping("")
    public String rentedMoviesPage(Model model) {
        List<RentedMovie> rentedMovies = (List<RentedMovie>) rentedMovieRepository.findAll();
        model.addAttribute("rentedMovies", rentedMovies);
        model.addAttribute("title", "Uthyrda filmer");
        return "rentmovies/rentedmovies";
    }

    @GetMapping("/rentmovie")
    public String rentMoviesPage(Model model){
        model.addAttribute("title", "Ny Uthyrning");
        return "rentmovies/rentmovie";
    }

    @PostMapping("/rentmovie")
    public String rentMovie(@RequestParam String socialSecurityNumber,
                            @RequestParam Long movie1,
                            @RequestParam Long movie2,
                            @RequestParam Long movie3,
                            Model model) {

        List<Movie> moviesToRent = Arrays.asList(
                movieRepository.findByProductNumber(movie1),
                movieRepository.findByProductNumber(movie2),
                movieRepository.findByProductNumber(movie3)
        );

        Customer c = customerRepository.findBySocialSecurityNumber(socialSecurityNumber);
        if (c == null) {
            model.addAttribute("errorMessage", "Kunden finns ej i systemet");
            return "rentmovies/rentmovie";
        }

        RentedMovie rm = new RentedMovie();
        rm.setCustomer(c);
//        rm.setRentedDate(LocalDate.now().minusDays(2));
        rentedMovieRepository.save(rm);

        moviesToRent.stream().forEach(m -> {
            m.setRentedMovie(rm);
            m.setRented(true);
        });
        movieRepository.saveAll(moviesToRent);

        return "redirect:/rentedmovies";
    }

    @GetMapping("/latemovies")
    public String getLateMovies(Model model) {
        List<RentedMovie> lateMovies = rentedMovieRepository.findByRentedDateLessThan(LocalDate.now().minusDays(1));
        model.addAttribute("lateMovies", lateMovies);
        model.addAttribute("title", "Försenade Inlämningar");
        return "rentmovies/latemovies";
    }
}
