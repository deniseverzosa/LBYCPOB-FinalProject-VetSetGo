package com.petpulse.controller;D

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebUIController {

    @GetMapping("/")
    public String showIndex() {
        return "index"; // Renders index.html
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login"; // Renders login.html
    }

    @GetMapping("/vet/dashboard")
    public String showVetDashboard() {
        return "dashboard"; // Renders your vet dashboard HTML
    }
}