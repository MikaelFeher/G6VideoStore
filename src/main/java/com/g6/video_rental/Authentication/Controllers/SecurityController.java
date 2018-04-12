package com.g6.video_rental.Authentication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
