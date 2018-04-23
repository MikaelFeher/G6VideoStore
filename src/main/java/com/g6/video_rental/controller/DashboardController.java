package com.g6.video_rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping("")
    public String getDashboard(){
        return "dashboard/dashboard";
    }
}
