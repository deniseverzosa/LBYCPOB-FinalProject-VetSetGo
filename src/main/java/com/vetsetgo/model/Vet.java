package com.vetsetgo.model;

import jakarta.persistence.Entity;

@Entity
public class Vet extends User {
    private String medicalLicense;

    // Default constructor required by Spring Data JPA
    public Vet() {}

    public Vet(String id, String name, String password, String medicalLicense) {
        super(id, name, password);
        this.medicalLicense = medicalLicense;
    }

    public String getMedicalLicense() {
        return medicalLicense;
    }

    @Override
    public String displayUserPortal() {
        return "Rendering Administrative Portal... Welcome Dr. " + getName() + " (License: " + medicalLicense + ")";
    }

    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }
}
