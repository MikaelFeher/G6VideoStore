package com.g6.video_rental.controller.rentmovie;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.Entities.RentalHistory;
import com.g6.video_rental.domain.Entities.RentedMovie;
import com.g6.video_rental.domain.repository.CustomerRepository;
import com.g6.video_rental.domain.repository.MovieRepository;
import com.g6.video_rental.domain.repository.RentalHistoryRepository;
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
    @Autowired
    RentalHistoryRepository rentalHistoryRepository;


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
                            @RequestParam (required = false) Long movie1,
                            @RequestParam (required = false) Long movie2,
                            @RequestParam (required = false) Long movie3,
                            Model model) {
        List<Movie> moviesToRent = new ArrayList<>();
        List<Movie> alreadyRented = new ArrayList<>();

        checkMovieRentability(movie1, moviesToRent, alreadyRented);
        checkMovieRentability(movie2, moviesToRent, alreadyRented);
        checkMovieRentability(movie3, moviesToRent, alreadyRented);

//        if (movie1 == null) {
//            // something
//        }else {
//            moviesToRent.add(movieRepository.findByProductNumber(movie1));
//        }
//        if (movie2 == null) {
//            // something
//        }else {
//            moviesToRent.add(movieRepository.findByProductNumber(movie2));
//        }
//        if (movie3 == null) {
//            // something
//        } else {
//            moviesToRent.add(movieRepository.findByProductNumber(movie3));
//        }

        Customer c = customerRepository.findBySocialSecurityNumber(socialSecurityNumber);
        if (c == null) {
            model.addAttribute("errorMessage", "Kunden finns ej i systemet");
            model.addAttribute("title", "Kunden finns ej");
            return "rentmovies/rentmovie";
        }

        if (alreadyRented.size() > 0) {
            model.addAttribute("alreadyRented", alreadyRented);
            model.addAttribute("title", "Fel vid uthyrning");
            return "rentmovies/rentmovie";
        }

        if(rentedMovieRepository.findByCustomer_SocialSecurityNumberAndReturnedDateIsNull(c.getSocialSecurityNumber()) != null){//
            model.addAttribute("errorMessage", "Kunden har redan en uthyrning");
            model.addAttribute("title", "Kunden har redan en uthyrning");
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

    private void checkMovieRentability(@RequestParam(required = false) Long movie2, List<Movie> moviesToRent, List<Movie> alreadyRented) {
        if (movie2 == null) {
            // Do nothing...
        }else {
            Movie m2 = movieRepository.findByProductNumber(movie2);
            if (m2.isRented()){
                alreadyRented.add(m2);
            } else {
                moviesToRent.add(m2);
            }
        }
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

    @PostMapping("/returnmovies")
    public String returnMovies(Model model, @RequestParam Long rentalId) {
        RentedMovie rental = rentedMovieRepository.findById(rentalId).get();
        Customer c = customerRepository.findBySocialSecurityNumber(rental.getCustomer().getSocialSecurityNumber());
        List<Movie> returnedMovies = new ArrayList<>();


        for (Movie movie : rental.getMovies()) {
            RentalHistory rh = new RentalHistory(rental.getId(), rental.getCustomer().getSocialSecurityNumber(), movie.getName(), rental.getRentedDate(), LocalDate.now());
            rentalHistoryRepository.save(rh);

            movie.setRented(false);
            movie.setRentedMovie(null);
            returnedMovies.add(movie);
        }

        movieRepository.saveAll(returnedMovies);

        returnedMovies.clear();
        rental.setReturnedDate(LocalDate.now());
        rental.setMovies(returnedMovies);
        rentedMovieRepository.save(rental);

//        List<RentedMovie> tmp = c.getRentedMovies();
//        tmp.clear();
        c.getRentedMovies().clear();
        customerRepository.save(c);
        c.getRentedMovies().stream().forEach(System.out::println);

        model.addAttribute("customer", c);

        return "rentmovies/returnmovies";
    }

    // Action for finding rented movies by socialsecnum and/or movie(name?)...
    // Action for returning rented movies...
}
