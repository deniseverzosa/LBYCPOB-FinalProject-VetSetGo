package com.vetsetgo.controller;

import com.vetsetgo.dto.PetOwnerSignUpDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<String> registerPetOwner(@RequestBody PetOwnerSignUpDTO dto) {
        if (dto.getEmail() == null || dto.getPassword() == null) {
            return ResponseEntity.badRequest().body("Sign up failed: Email and password are required.");
        }
        return ResponseEntity.ok("Validation passed. Ready to save.");
    }
}