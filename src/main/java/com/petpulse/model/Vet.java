package com.petpulse.model;

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
    public void setMedicalLicense(String medicalLicense) {
        this.medicalLicense = medicalLicense;
    }
}
