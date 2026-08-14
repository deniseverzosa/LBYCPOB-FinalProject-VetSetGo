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
}