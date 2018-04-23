package com.g6.video_rental.controller.Customer;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RentedMovieRepository rentedMovieRepository;
    @Autowired
    private CustomerQueryRepository customerQueryRepository;
    
    @GetMapping("/customer")
    public String getCustomer(@RequestParam String socialSecurityNumber, Model model) {
        model.addAttribute("customer", customerRepository.findBySocialSecurityNumber(socialSecurityNumber));
        Customer customer = customerRepository.findBySocialSecurityNumber(socialSecurityNumber).get(0);
        model.addAttribute("rentedmovies", rentedMovieRepository.findByCustomer(customer));
        model.addAttribute("rentedmovieshistory", customer.getRentedMovies());
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
        return "customer/customers";
    }

    /*@RequestMapping("/returnmovies")
    public String getReturnMovies(@RequestParam String socialSecurityNumber, Model model) {
        List<RentedMovie> rentedMovie;
        Customer tempCustomer = customerRepository.findBySocialSecurityNumber(socialSecurityNumber).get(0);
        rentedMovie = rentedMovieRepository.findByCustomer(tempCustomer);
        tempCustomer.setRentedMovies(rentedMovie);
        for(RentedMovie rented : rentedMovie) {
            //rentedMovieQueryRepository.deleteRentedMovie(rented.getId());
            rented.setCustomer(null);
            rentedMovieRepository.save(rented);
        }
        return "redirect:/customer/customer?socialSecurityNumber=" + tempCustomer.getSocialSecurityNumber();
    }*/

    @RequestMapping("/modifycustomer")
    public String modifyCustomer(Customer customer) {
        updateCustomerTable(customer);
        return "redirect:/customer/customer?socialSecurityNumber=" + customer.getSocialSecurityNumber();
    }
    
    @RequestMapping("/addcustomer")
    public String addCustomer(Model model) {
        return "customer/addcustomer";
    }
    
    @PostMapping("/addcustomer")
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer/customers";
    }

    @GetMapping("/searchcustomers")
    public String searchCustomer(Model model, @RequestParam(required = false) String socialSecurityNumber, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return "redirect:/customer/customers?socialSecurityNumber=" + socialSecurityNumber + "&firstName=" + firstName + "&lastName=" + lastName;
    }

    private void updateCustomerTable(Customer customer) {
        String socialSecurity = customer.getSocialSecurityNumber();
        customerQueryRepository.updateFirstName(customer.getFirstName(), socialSecurity);
        customerQueryRepository.updateLastName(customer.getLastName(), socialSecurity);
        customerQueryRepository.updateAddress(customer.getAddress(), socialSecurity);
        customerQueryRepository.updateZipCode(customer.getZipCode(), socialSecurity);
        customerQueryRepository.updateCity(customer.getCity(), socialSecurity);
        customerQueryRepository.updateCountry(customer.getCountry(), socialSecurity);
        customerQueryRepository.updatePhoneNumber(customer.getPhoneNumber(), socialSecurity);
        customerQueryRepository.updateEmail(customer.getEmail(), socialSecurity);
    }
}
