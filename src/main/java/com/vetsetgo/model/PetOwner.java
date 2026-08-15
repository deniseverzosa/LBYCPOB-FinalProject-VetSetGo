package com.vetsetgo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PetOwner extends User {

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;

    // Default constructor required by Spring Data JPA
    public PetOwner() {}

    public PetOwner(String id, String name, String password, String email, String phoneNumber) {
        super(id, name, password, email, phoneNumber);
        this.pets = new ArrayList<>();
    }

    @Override
    public String displayUserPortal() {
        return "Rendering Personal Dashboard... Welcome, " + getName();
    }

    public List<Pet> getPets() {
        return pets;
    }
    public void addPet(Pet pet) {
        this.pets.add(pet);
        pet.setOwner(this);
    }
}
