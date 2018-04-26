package com.g6.video_rental.controller;


import com.g6.video_rental.domain.Entities.RentedMovie;

import com.g6.video_rental.domain.repository.RentedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {


    @Autowired
    RentedMovieRepository rentedMovieRepository;


    @GetMapping("")
    public String getDashboard(Model model){
        List<RentedMovie> lateMovies = rentedMovieRepository.findByRentedDateLessThanAndReturnedDateNull(LocalDate.now().minusDays(1));
        model.addAttribute("lateMovies", lateMovies);
        model.addAttribute("title", "Försenade filmer");

        return "dashboard/dashboard";
    }
}
