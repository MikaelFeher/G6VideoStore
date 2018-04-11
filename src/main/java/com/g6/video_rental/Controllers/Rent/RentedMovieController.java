package com.g6.video_rental.Controllers.Rent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rent")
public class RentedMovieController {
    @GetMapping("/rented-movie")
    public String getRentedMovie() {
        return "rent/rented-movie";
    }
}
