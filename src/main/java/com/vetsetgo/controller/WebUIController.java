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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private final Vet dummyVet;
    private final PetOwner dummyOwner;
    private final List<Appointment> dummyAppointments;

    public WebUIController() {
        dummyOwner = new PetOwner("O101", "Alice Johnson", "pass123", "alice@email.com", "555-1234");
        dummyVet = new Vet("V202", "Dr. Bob Miller", "vetpass", "drbob@email.com", "555-9876", "VET-LICENSE-99");

        Pet pet = new Pet("Luna", "Feline", "Domestic Shorthair");
        pet.setAge(2);
        pet.setWeight(4.5);

        MedicalRecord record = new MedicalRecord("Routine Checkup: All clear", "None required", "HR: 120bpm, Temp: 38.5C");
        pet.addMedicalRecord(record);

        dummyOwner.addPet(pet);

        Appointment appt = new Appointment("A-1", dummyVet, dummyOwner, pet, LocalDateTime.now().plusDays(2).withHour(10).withMinute(0));
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

    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

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

    @GetMapping("/vet/dashboard")
    public String showVetDashboard(Model model) {
        model.addAttribute("user", dummyVet);
        model.addAttribute("appointments", dummyAppointments);
        return "vet/dashboard";
    }

    @GetMapping("/owner/dashboard")
    public String showOwnerDashboard(Model model, HttpSession session) {
        String loggedInUserId = (String) session.getAttribute("loggedInUserId");
        if (loggedInUserId != null && petOwnerRepository.existsById(loggedInUserId)) {
            PetOwner realOwner = petOwnerRepository.findById(loggedInUserId).get();
            model.addAttribute("user", realOwner);
            model.addAttribute("pets", realOwner.getPets());
            return "owner/dashboard";
        }
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

        List<Appointment> petAppointments = dummyAppointments.stream()
                .filter(a -> a.getPet().getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        model.addAttribute("appointments", petAppointments);

        return "owner/pet-profile";
    }

    @PostMapping("/owner/book-appointment")
    public String bookAppointment(@RequestParam("petName") String petName,
                                  @RequestParam("timeSlot") String timeSlotStr) {
        Pet targetPet = findPetByName(petName);
        if (targetPet != null) {
            LocalDateTime dateTime = LocalDateTime.parse(timeSlotStr);
            int hour = dateTime.getHour();

            if (hour < 9 || hour >= 17) {
                return "redirect:/owner/dashboard?error=invalidTime";
            }

            String newApptId = "A-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
            Appointment newAppt = new Appointment(newApptId, dummyVet, dummyOwner, targetPet, dateTime);
            dummyAppointments.add(newAppt);
        }
        return "redirect:/owner/dashboard";
    }

    @PostMapping("/vet/update-appointment")
    public String updateAppointmentStatus(@RequestParam("appointmentId") String appointmentId,
                                          @RequestParam("status") AppointmentStatus status) {
        for (Appointment appt : dummyAppointments) {
            if (appt.getId().equals(appointmentId)) {
                appt.setStatus(status);
                break;
            }
        }
        return "redirect:/vet/dashboard";
    }

    @PostMapping("/vet/add-medical-record")
    public String addMedicalRecord(@RequestParam("petName") String petName,
                                   @RequestParam("diagnosisNotes") String diagnosisNotes,
                                   @RequestParam("medicineDosages") String medicineDosages,
                                   @RequestParam("vitalSigns") String vitalSigns) {
        Pet targetPet = findPetByName(petName);
        if (targetPet != null) {
            MedicalRecord newRecord = new MedicalRecord(diagnosisNotes, medicineDosages, vitalSigns);
            targetPet.addMedicalRecord(newRecord);
        }
        return "redirect:/vet/medical-history?petId=" + petName;
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