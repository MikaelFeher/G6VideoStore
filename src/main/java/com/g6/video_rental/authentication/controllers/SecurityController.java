package com.g6.video_rental.authentication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("title", "Inloggning");
        return "login";
    }
}
