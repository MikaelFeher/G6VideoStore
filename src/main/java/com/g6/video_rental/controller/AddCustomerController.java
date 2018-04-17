package com.g6.video_rental.controller;

import com.g6.video_rental.domain.Entities.Customer;
import com.g6.video_rental.domain.repository.CustomerQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class AddCustomerController {
    
    @Autowired
    private CustomerQueryRepository customerQueryRepository;
    
    @RequestMapping("/addcustomer")
    public String redirect(Customer customer) {
        updateCustomerTable(customer);
        return "redirect:/customer/customer?socialSecurityNumber=" + customer.getSocialSecurityNumber();
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
