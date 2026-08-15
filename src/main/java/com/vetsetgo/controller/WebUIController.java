package com.vetsetgo.controller;

import com.vetsetgo.model.*;
import com.vetsetgo.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class WebUIController {

    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/")
    public String showIndex() { return "index"; }

    @GetMapping("/login")
    public String showLogin() { return "login"; }

    @GetMapping("/signup")
    public String showSignUpPage() { return "signup"; }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session) {

        Optional<Vet> vetOpt = vetRepository.findById(username);
        if (vetOpt.isPresent() && vetOpt.get().getPassword().equals(password)) {
            session.setAttribute("loggedInUserId", username);
            return "redirect:/vet/dashboard";
        }

        Optional<PetOwner> ownerOpt = petOwnerRepository.findById(username);
        if (ownerOpt.isPresent() && ownerOpt.get().getPassword().equals(password)) {
            session.setAttribute("loggedInUserId", username);
            return "redirect:/owner/dashboard";
        }

        return "redirect:/login?error";
    }

    @GetMapping("/owner/dashboard")
    public String showOwnerDashboard(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loggedInUserId");
        if (userId == null) return "redirect:/login";

        Optional<PetOwner> ownerOpt = petOwnerRepository.findById(userId);
        if (ownerOpt.isPresent()) {
            PetOwner owner = ownerOpt.get();
            model.addAttribute("user", owner);
            model.addAttribute("pets", owner.getPets());
            model.addAttribute("vets", vetRepository.findAll());
            return "owner/dashboard";
        }
        return "redirect:/login";
    }

    @PostMapping("/owner/add-pet")
    public String addPet(@RequestParam("name") String name,
                         @RequestParam("species") String species,
                         @RequestParam("breed") String breed,
                         @RequestParam("age") int age,
                         @RequestParam("weight") double weight,
                         HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUserId");
        Optional<PetOwner> ownerOpt = petOwnerRepository.findById(userId);

        if (ownerOpt.isPresent()) {
            PetOwner owner = ownerOpt.get();
            Pet newPet = new Pet(name, species, breed);
            newPet.setAge(age);
            newPet.setWeight(weight);

            petRepository.save(newPet);
            owner.addPet(newPet);
            petOwnerRepository.save(owner);
        }
        return "redirect:/owner/dashboard";
    }

    @GetMapping("/vet/dashboard")
    public String showVetDashboard(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loggedInUserId");
        if (userId == null) return "redirect:/login";

        Optional<Vet> vetOpt = vetRepository.findById(userId);
        if (vetOpt.isPresent()) {
            model.addAttribute("user", vetOpt.get());
            model.addAttribute("appointments", appointmentRepository.findAll());
            return "vet/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/owner/pet-profile")
    public String showPetProfile(@RequestParam("name") String name, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loggedInUserId");
        Optional<PetOwner> ownerOpt = petOwnerRepository.findById(userId);
        if (ownerOpt.isEmpty()) return "redirect:/login";

        PetOwner owner = ownerOpt.get();
        model.addAttribute("user", owner);

        Pet targetPet = owner.getPets().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        model.addAttribute("pet", targetPet);
        model.addAttribute("records", targetPet != null ? targetPet.getMedicalRecords() : new ArrayList<>());
        model.addAttribute("appointments", new ArrayList<>());

        return "owner/pet-profile";
    }

    @PostMapping("/owner/book-appointment")
    public String bookAppointment(@RequestParam("petName") String petName,
                                  @RequestParam("vetId") String vetId,
                                  @RequestParam("timeSlot") String timeSlotStr,
                                  HttpSession session) {
        String userId = (String) session.getAttribute("loggedInUserId");
        Optional<PetOwner> ownerOpt = petOwnerRepository.findById(userId);
        Optional<Vet> selectedVetOpt = vetRepository.findById(vetId);

        if (ownerOpt.isPresent() && selectedVetOpt.isPresent()) {
            PetOwner owner = ownerOpt.get();
            Pet targetPet = owner.getPets().stream().filter(p -> p.getName().equalsIgnoreCase(petName)).findFirst().orElse(null);

            if (targetPet != null) {
                LocalDateTime dateTime = LocalDateTime.parse(timeSlotStr);
                String newApptId = "A-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
                Appointment newAppt = new Appointment(newApptId, selectedVetOpt.get(), owner, targetPet, dateTime);
                appointmentRepository.save(newAppt);
            }
        }
        return "redirect:/owner/dashboard";
    }

    @GetMapping("/vet/medical-history")
    public String showMedicalHistory(@RequestParam("petId") String petId, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loggedInUserId");
        if (userId == null) return "redirect:/login";

        Optional<Vet> vetOpt = vetRepository.findById(userId);
        if (vetOpt.isEmpty()) return "redirect:/login";

        model.addAttribute("user", vetOpt.get());

        Pet targetPet = petRepository.findAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(petId))
                .findFirst()
                .orElse(null);

        model.addAttribute("pet", targetPet);
        model.addAttribute("records", targetPet != null ? targetPet.getMedicalRecords() : new ArrayList<>());
        return "vet/medical-history";
    }

    @PostMapping("/vet/update-appointment")
    public String updateAppointmentStatus(@RequestParam("appointmentId") String appointmentId,
                                          @RequestParam("status") AppointmentStatus status) {
        Optional<Appointment> apptOpt = appointmentRepository.findById(appointmentId);
        if (apptOpt.isPresent()) {
            Appointment appt = apptOpt.get();
            appt.setStatus(status);
            appointmentRepository.save(appt);
        }
        return "redirect:/vet/dashboard";
    }

    // FIX: Save new medical records to the specific pet in the database
    @PostMapping("/vet/add-medical-record")
    public String addMedicalRecord(@RequestParam("petName") String petName,
                                   @RequestParam("diagnosisNotes") String diagnosisNotes,
                                   @RequestParam("medicineDosages") String medicineDosages,
                                   @RequestParam("vitalSigns") String vitalSigns) {

        Pet targetPet = petRepository.findAll().stream()
                .filter(p -> p.getName().equalsIgnoreCase(petName))
                .findFirst()
                .orElse(null);

        if (targetPet != null) {
            MedicalRecord newRecord = new MedicalRecord(diagnosisNotes, medicineDosages, vitalSigns);
            targetPet.addMedicalRecord(newRecord);
            petRepository.save(targetPet); // Update the DB
        }

        return "redirect:/vet/medical-history?petId=" + petName;
    }
}