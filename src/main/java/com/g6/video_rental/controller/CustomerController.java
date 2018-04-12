package com.g6.video_rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @RequestMapping("/customer")
    public String getCustomer() {
        return "customer/customer";
    }
}
