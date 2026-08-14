package com.petpulse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PetOwner extends User {

    // @Transient tells JPA to ignore this field in the database for now
    // until Pet is also converted into an @Entity
    @Transient
    private List<Pet> pets;

    // Default constructor required by Spring Data JPA
    public PetOwner() {}

    public PetOwner(String id, String name, String password) {
        super(id, name, password);
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
    }
}
