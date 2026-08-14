package com.petpulse.model;

import java.util.ArrayList;
import java.util.List;

public class PetOwner extends User {
    private List<Pet> pets;

    public PetOwner(String id, String name, String password) {
        super(id, name, password);
        this.pets = new ArrayList<>();
    }

    public List<Pet> getPets() {
        return pets;
    }
    public void addPet(Pet pet) {
        this.pets.add(pet);
    }
}
