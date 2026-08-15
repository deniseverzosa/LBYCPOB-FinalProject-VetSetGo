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
        return "index";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if ("V202".equals(username) && "vetpass".equals(password)) {
            return "redirect:/vet/dashboard";
        } else if ("O101".equals(username) && "pass123".equals(password)) {
            return "redirect:/owner/dashboard";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/vet/dashboard")
    public String showVetDashboard(Model model) {
        model.addAttribute("user", dummyVet);
        model.addAttribute("appointments", dummyAppointments);
        return "vet/dashboard";
    }

    @GetMapping("/owner/dashboard")
    public String showOwnerDashboard(Model model) {
        model.addAttribute("user", dummyOwner);
        model.addAttribute("pets", dummyOwner.getPets());
        return "owner/dashboard";
    }

    @GetMapping("/vet/medical-history")
    public String showMedicalHistory(@RequestParam("petId") String petId, Model model) {
        Pet targetPet = findPetByName(petId);

        model.addAttribute("pet", targetPet);
        model.addAttribute("user", dummyVet);

        if (targetPet != null && targetPet.getMedicalRecords() != null) {
            model.addAttribute("records", targetPet.getMedicalRecords());
        } else {
            model.addAttribute("records", new ArrayList<MedicalRecord>());
        }

        return "vet/medical-history";
    }

    // FIX: Added explicit ("name") name
    @GetMapping("/owner/pet-profile")
    public String showPetProfile(@RequestParam("name") String name, Model model) {
        Pet targetPet = findPetByName(name);

        model.addAttribute("pet", targetPet);
        model.addAttribute("user", dummyOwner);

        if (targetPet != null && targetPet.getMedicalRecords() != null) {
            model.addAttribute("records", targetPet.getMedicalRecords());
        } else {
            model.addAttribute("records", new ArrayList<MedicalRecord>());
        }

        model.addAttribute("appointments", dummyAppointments);

        return "owner/pet-profile";
    }

    private Pet findPetByName(String name) {
        for (Pet p : dummyOwner.getPets()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
}