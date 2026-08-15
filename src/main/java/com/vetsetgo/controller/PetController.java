package com.vetsetgo.controller;

import com.vetsetgo.dto.PetDTO;
import com.vetsetgo.model.PetOwner;
import com.vetsetgo.repository.PetOwnerRepository;
import com.vetsetgo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetOwnerRepository petOwnerRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerPet(@RequestBody PetDTO petDTO) {
        if (petDTO.getOwnerId() == null || petDTO.getOwnerId().isEmpty()) {
            return ResponseEntity.badRequest().body("Validation Error: ownerId is required.");
        }
        Optional<PetOwner> ownerOpt = petOwnerRepository.findById(petDTO.getOwnerId());
        if (ownerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Pet Owner not found.");
        }
        PetOwner owner = ownerOpt.get();
        return ResponseEntity.ok("Owner verified. Ready for pet registration.");
    }
}