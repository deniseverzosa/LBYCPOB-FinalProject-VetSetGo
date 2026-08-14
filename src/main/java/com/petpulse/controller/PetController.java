package com.petpulse.controller;

import com.petpulse.dto.PetDTO;
import com.petpulse.model.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @PostMapping("/register")
    public ResponseEntity<?> registerPet(@RequestBody PetDTO petDTO) {
        try {
            // Mapping DTO to Domain Entity
            Pet pet = new Pet(petDTO.getName(), petDTO.getSpecies());
            pet.setAge(petDTO.getAge());
            pet.setWeight(petDTO.getWeight());

            return ResponseEntity.ok("Successfully registered pet: " + pet.getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation Error: " + e.getMessage());
        }
    }
}