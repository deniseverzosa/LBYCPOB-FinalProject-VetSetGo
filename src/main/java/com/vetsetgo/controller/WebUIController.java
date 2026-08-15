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

    private final Vet dummyVet;
    private final PetOwner dummyOwner;
    private final List<Appointment> dummyAppointments;

    public WebUIController() {
        // Initialize Owner and Vet
        dummyOwner = new PetOwner("O101", "Alice Johnson", "pass123", "alice@email.com", "555-1234");
        dummyVet = new Vet("V202", "Dr. Bob Miller", "vetpass", "drbob@email.com", "555-9876", "VET-LICENSE-99");

        // Initialize Pet
        Pet pet = new Pet("Luna", "Feline", "Domestic Shorthair");
        pet.setAge(2);
        pet.setWeight(4.5);

        // Initialize Medical Record
        MedicalRecord record = new MedicalRecord("Routine Checkup: All clear", "None required", "HR: 120bpm, Temp: 38.5C");
        pet.addMedicalRecord(record);

        // Add pet to owner
        dummyOwner.addPet(pet);

        // Initialize Appointments
        Appointment appt = new Appointment("A-1", dummyVet, dummyOwner, pet, LocalDateTime.now().plusDays(2));
        dummyAppointments = new ArrayList<>();
        dummyAppointments.add(appt);

        dummyVet.addAppointment(appt);
    }

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