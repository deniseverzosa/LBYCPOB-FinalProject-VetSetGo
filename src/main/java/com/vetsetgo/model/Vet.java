package com.vetsetgo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vet extends User {
    private String medicalLicense;

    @ElementCollection
    private List<String> availableShifts;

    @OneToMany(mappedBy = "vet", cascade = CascadeType.ALL)
    private List<Appointment> upcomingAppointments;

    public Vet() {}

    public Vet(String id, String name, String password, String email, String phoneNumber, String medicalLicense) {
        super(id, name, password, email, phoneNumber);
        this.medicalLicense = medicalLicense;
        this.availableShifts = new ArrayList<>();
        this.upcomingAppointments = new ArrayList<>();
    }

    @Override
    public String displayUserPortal() {
        return "Rendering Administrative Portal... Welcome Dr. " + getName() + " (License: " + medicalLicense + ")";
    }

    public String getMedicalLicense() { return medicalLicense; }
    public void setMedicalLicense(String medicalLicense) { this.medicalLicense = medicalLicense; }
}