package com.petpulse.controller;

import com.petpulse.dto.AppointmentDTO;
import com.petpulse.utils.DateTimeUtil;
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
}