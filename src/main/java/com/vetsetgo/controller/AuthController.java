package com.vetsetgo.controller;

import com.vetsetgo.dto.PetOwnerSignUpDTO;
import com.vetsetgo.model.PetOwner;
import com.vetsetgo.repository.PetOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> registerPetOwner(@RequestBody PetOwnerSignUpDTO dto) {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            return ResponseEntity.badRequest().body("Sign up failed: Email and password are required.");
        }
        String generatedId = "O-" + UUID.randomUUID().toString().substring(0, 8);
        PetOwner newOwner = new PetOwner(
                generatedId,
                dto.getName(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getPhoneNumber()
        );
        petOwnerRepository.save(newOwner);
        return ResponseEntity.ok("Sign up successful! Welcome to the PetPulse portal, " + newOwner.getName() + ". Your ID is: " + generatedId);
    }
}