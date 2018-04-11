package com.g6.video_rental.Controllers.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomersController {
    @GetMapping("/customers")
    public String getCustomers() {
        return "customer/customers";
    }
}
