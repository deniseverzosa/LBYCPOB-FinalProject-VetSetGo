package com.petpulse.model;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private String name;
    private String species;
    private int age;
    private double weight;
    private List<MedicalRecord> medicalHistory;

    public Pet(String name, String species) {
        this.name = name;
        this.species = species;
        this.medicalHistory = new ArrayList<>();
    }

    // Encapsulation: Strict validation guards to prevent negative age values
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid state: Age cannot be negative.");
        }
        this.age = age;
    }
}