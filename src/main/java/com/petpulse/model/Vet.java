package com.petpulse.model;

public class Vet extends User {
    private String medicalLicense;

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
