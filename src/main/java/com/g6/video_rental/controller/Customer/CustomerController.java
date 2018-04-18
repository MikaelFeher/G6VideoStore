package com.g6.video_rental.controller.Customer;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.repository.CustomerRepository;
import com.g6.video_rental.domain.repository.RentedMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("customer")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RentedMovieRepository rentedMovieRepository;
    
    @GetMapping("/customer")
    public String getCustomer(@RequestParam String socialSecurityNumber, Model model) {
        model.addAttribute("customer", customerRepository.findBySocialSecurityNumber(socialSecurityNumber));
        Customer customer = customerRepository.findBySocialSecurityNumber(socialSecurityNumber).get(0);
        model.addAttribute("rentedmovies", rentedMovieRepository.findByCustomer(customer));
        model.addAttribute("rentedmovieshistory", customer.getRentedMovies());
        System.out.println("****************************************" + customer.getRentedMovies());
        return "customer/customer";
    }
}
