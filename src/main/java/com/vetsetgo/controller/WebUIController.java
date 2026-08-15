package com.vetsetgo.controller;

import com.vetsetgo.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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