package com.vetsetgo.controller;

import com.vetsetgo.dto.PetDTO;
import com.vetsetgo.model.Pet;
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

        try {
            String breed = (petDTO.getBreed() != null && !petDTO.getBreed().isEmpty()) ? petDTO.getBreed() : "Not Specified";
            Pet pet = new Pet(petDTO.getName(), petDTO.getSpecies(), breed);
            pet.setAge(petDTO.getAge());
            pet.setWeight(petDTO.getWeight());
            pet.setAllergies(petDTO.getAllergies());
            pet.setOwner(owner);
            petRepository.save(pet);
            return ResponseEntity.ok("Successfully registered pet: " + pet.getName() + " for owner: " + owner.getName());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation Error: " + e.getMessage());
        }
    }
}