package com.vetsetgo.model;

public class Vaccination extends MedicalService {
    private String vaccineName;

    public Vaccination(String vaccineName, double baseFee) {
        super("Vaccination", baseFee);
        this.vaccineName = vaccineName;
    }

    @Override
    public double calculateCost() {
        return getBaseFee(); // Fixed price for vaccine
    }

    @Override
    public String getDetails() {
        return "Administered " + vaccineName + " vaccine";
    }
}