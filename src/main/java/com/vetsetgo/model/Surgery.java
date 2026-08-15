package com.vetsetgo.model;

public class Surgery extends MedicalService {
    private double anesthesiaFee;

    public Surgery(String surgeryType, double baseFee, double anesthesiaFee) {
        super("Surgery (" + surgeryType + ")", baseFee);
        this.anesthesiaFee = anesthesiaFee;
    }

    @Override
    public double calculateCost() {
        return getBaseFee() + anesthesiaFee; // Base surgical fee plus additional fees
    }

    @Override
    public String getDetails() {
        return getServiceName() + " [Anesthesia: $" + anesthesiaFee + "]";
    }
}
