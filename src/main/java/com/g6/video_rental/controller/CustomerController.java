package com.g6.video_rental.controller;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("customer")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @GetMapping("/customer")
    public String getCustomer(@RequestParam String socialSecurityNumber, Model model) {
        model.addAttribute("customer", customerRepository.findBySocialSecurityNumber(socialSecurityNumber));
        return "customer/customer";
    }
}
