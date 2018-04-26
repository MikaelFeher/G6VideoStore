package com.g6.video_rental.controller.Customer;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.Entities.Movie;
import com.g6.video_rental.domain.Entities.RentedMovie;
import com.g6.video_rental.domain.Entities.RentalHistory;
import com.g6.video_rental.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RentedMovieRepository rentedMovieRepository;
    @Autowired
    private RentalHistoryRepository rentalHistoryRepository;
    
    @GetMapping("/customer")
    public String getCustomer(@RequestParam String socialSecurityNumber, Model model) {
        Customer customer = customerRepository.findBySocialSecurityNumber(socialSecurityNumber);
        List<RentalHistory> rentalHistory = rentalHistoryRepository.findBySocialSecurityNumber(customer.getSocialSecurityNumber());

        model.addAttribute("customer", customer);
        model.addAttribute("rentedmovies", rentedMovieRepository.findByCustomer_SocialSecurityNumberAndReturnedDateIsNull(customer.getSocialSecurityNumber()));
        model.addAttribute("rentedmovieshistory", rentalHistory);
        model.addAttribute("title", "Kund: " + customer.getFirstName() + " " + customer.getLastName());
        return "customer/customer";
    }
    
    @GetMapping("/customers")
    public String getCustomers(Model model, @RequestParam(required = false) String socialSecurityNumber, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        if(socialSecurityNumber == null && firstName == null && lastName == null) {
            model.addAttribute("customers", customerRepository.findAll());
        }
        else {
            List<Customer> filteredCustomers = customerRepository.findBySocialSecurityNumberContainsIgnoreCaseAndFirstNameContainsIgnoreCaseAndLastNameContains(socialSecurityNumber, firstName, lastName);
            model.addAttribute("customers", filteredCustomers);
        }
        model.addAttribute("title", "Alla kunder");
        return "customer/customers";
    }

    @PostMapping("/customer")
    public String modifyCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer/customer?socialSecurityNumber=" + customer.getSocialSecurityNumber();
    }
    
    @RequestMapping("/addcustomer")
    public String addCustomer(Model model) {
        model.addAttribute("title", "Lägg till ny kund");
        return "customer/addcustomer";
    }
    
    @PostMapping("/addcustomer")
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer/customers";
    }

    @PostMapping("/customers")
    public String searchCustomer(Model model, @RequestParam(required = false) String socialSecurityNumber, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return "redirect:/customer/customers?socialSecurityNumber=" + socialSecurityNumber + "&firstName=" + firstName + "&lastName=" + lastName;
    }
}
