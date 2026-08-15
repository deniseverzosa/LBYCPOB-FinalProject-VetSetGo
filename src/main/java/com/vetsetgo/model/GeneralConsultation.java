package com.vetsetgo.model;

public class GeneralConsultation extends MedicalService {
    private String notes;

    public GeneralConsultation(double baseFee, String notes) {
        super("General Consultation", baseFee);
        this.notes = notes;
    }

    @Override
    public double calculateCost() {
        return getBaseFee();
    }

    @Override
    public String getDetails() {
        return "Consultation: " + notes;
    }
}