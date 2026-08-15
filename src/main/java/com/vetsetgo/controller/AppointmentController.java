package com.vetsetgo.controller;

import com.vetsetgo.dto.AppointmentDTO;
import com.vetsetgo.model.Vet;
import com.vetsetgo.repository.VetRepository;
import com.vetsetgo.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private VetRepository vetRepository;

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDTO dto) {
        if (!DateTimeUtil.isWithinClinicHours(dto.getTimeSlot())) {
            return ResponseEntity.badRequest().body("Failed to book: Appointments must be during clinic hours (9 AM - 5 PM).");
        }
        Optional<Vet> vetOptional = vetRepository.findById(dto.getVetId());
        if (vetOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to book: Vet not found in the system.");
        }
        Vet requestedVet = vetOptional.get();

        if (!requestedVet.isAvailableFor(dto.getTimeSlot())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Failed to book: Dr. " + requestedVet.getName() + " is already booked at this time.");
        }
        return ResponseEntity.ok("Appointment confirmed for " + dto.getPetName() + " on " + dto.getTimeSlot());
    }
}