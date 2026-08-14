package com.vetsetgo.model;

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

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid state: Weight must be greater than 0.");
        }
        this.weight = weight;
    }

    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }

    public void addMedicalRecord(MedicalRecord record) {
        this.medicalHistory.add(record);
    }
    public List<MedicalRecord> getMedicalHistory() { return medicalHistory; }
}