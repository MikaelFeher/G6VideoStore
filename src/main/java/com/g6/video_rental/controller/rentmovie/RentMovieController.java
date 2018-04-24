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
import java.util.ArrayList;
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

    @GetMapping("/{rentalId}/rentaldetails")
    public String getRentalDetails(Model model, @PathVariable Long rentalId) {
        RentedMovie rental = rentedMovieRepository.findById(rentalId).get();
        Customer customer = customerRepository.findBySocialSecurityNumber(rental.getCustomer().getSocialSecurityNumber());
        List<Movie> rentedMovies = new ArrayList<>();
        rental.getMovies().stream().forEach(m -> rentedMovies.add(m));

        model.addAttribute("customer", customer);
        model.addAttribute("rental", rental);
        model.addAttribute("movies", rentedMovies);
        model.addAttribute("tableTitle", "Hyrda Filmer");
        model.addAttribute("title", "Uthyrningsinformation");
        return "rentmovies/rentaldetails";
    }

    @GetMapping("/rentmovie")
    public String rentMoviesPage(Model model) {
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
         /*
         No reason to check if the movies to be rented are null/rented since the spec states that each movie has its
         productNumber labelled on the physical box, can't rent a movie the store clerk isn't holding in his/her hands...
         */

        Customer c = customerRepository.findBySocialSecurityNumber(socialSecurityNumber);
        if (c == null) {
            model.addAttribute("errorMessage", "Kunden finns ej i systemet");
            return "rentmovies/rentmovie";
        }

        RentedMovie rm = new RentedMovie();
        rm.setCustomer(c);
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
        List<RentedMovie> lateMovies = rentedMovieRepository.findByRentedDateLessThanAndReturnedDateNull(LocalDate.now().minusDays(1));
        model.addAttribute("lateMovies", lateMovies);
        model.addAttribute("title", "Försenade Inlämningar");
        return "rentmovies/latemovies";
    }

    @GetMapping("/latemovies/{rentalId}/laterentaldetails")
    public String getLateRentalDetails(Model model, @PathVariable Long rentalId) {
        RentedMovie lateRental = rentedMovieRepository.findById(rentalId).get();
        Customer customer = customerRepository.findBySocialSecurityNumber(lateRental.getCustomer().getSocialSecurityNumber());
        List<Movie> lateMovies = new ArrayList<>();

        lateRental.getMovies().stream().forEach(m -> lateMovies.add(m));

        model.addAttribute("customer", customer);
        model.addAttribute("rental", lateRental);
        model.addAttribute("movies", lateMovies);
        model.addAttribute("tableTitle", "Försenade Filmer");
        model.addAttribute("title", "Förseningsinformation");

        return "rentmovies/laterentaldetails";
    }

    // Action for finding rented movies by socialsecnum and/or movie(name?)...
    // Action for returning rented movies...
}
