package com.vetsetgo.controller;

import com.vetsetgo.dto.AppointmentDTO;
import com.vetsetgo.utils.DateTimeUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentDTO dto) {
        if (!DateTimeUtil.isWithinClinicHours(dto.getTimeSlot())) {
            return ResponseEntity.badRequest().body("Failed to book: Appointments must be during clinic hours (9 AM - 5 PM).");
        }
        return ResponseEntity.ok("Appointment confirmed for " + dto.getPetName() + " on " + dto.getTimeSlot());
    }
    //  Double-booking prevention logic
    // TODO: Uncomment this when you have your VetService/Repository ready
    // Vet requestedVet = vetRepository.findById(dto.getVetId());
    //
    // if (!requestedVet.isAvailableFor(dto.getTimeSlot())) {
    //     return ResponseEntity.status(HttpStatus.CONFLICT)
    //            .body("Failed to book: The doctor is already booked at this time.");
    // }
}