package com.g6.video_rental.controller;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomersController {
    @Autowired
    private CustomerRepository customerRepository;

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
}