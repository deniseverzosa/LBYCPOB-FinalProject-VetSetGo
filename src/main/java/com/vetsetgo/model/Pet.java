package com.vetsetgo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String breed;
    private int age;
    private double weight;

    @ManyToOne
    private PetOwner owner;

    // Field name aligned with standard naming
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    public Pet() {}

    public Pet(String name, String species, String breed) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.medicalRecords = new ArrayList<>();
    }

    // Encapsulation Rules
    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative.");
        this.age = age;
    }

    public void setWeight(double weight) {
        if (weight <= 0) throw new IllegalArgumentException("Weight must be greater than 0.");
        this.weight = weight;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    public PetOwner getOwner() { return owner; }
    public void setOwner(PetOwner owner) { this.owner = owner; }
    public List<MedicalRecord> getMedicalRecords() { return this.medicalRecords; }
    public void setMedicalRecords(List<MedicalRecord> medicalRecords) { this.medicalRecords = medicalRecords; }

    public void addMedicalRecord(MedicalRecord record) {
        if (this.medicalRecords == null) {
            this.medicalRecords = new ArrayList<>();
        }
        this.medicalRecords.add(record);
        record.setPet(this);
    }
}