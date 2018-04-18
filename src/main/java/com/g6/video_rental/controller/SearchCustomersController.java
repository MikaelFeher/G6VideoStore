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
public class SearchCustomersController {
    @GetMapping("/searchcustomers")
    public String searchCustomer(Model model, @RequestParam(required = false) String socialSecurityNumber, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return "redirect:/customer/customers?socialSecurityNumber=" + socialSecurityNumber + "&firstName=" + firstName + "&lastName=" + lastName;
    }
}
