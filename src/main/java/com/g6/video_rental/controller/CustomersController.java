package com.g6.video_rental.controller;

import com.g6.video_rental.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("customer")
public class CustomersController {
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/customers")
    public String getCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer/customers";
    }
}