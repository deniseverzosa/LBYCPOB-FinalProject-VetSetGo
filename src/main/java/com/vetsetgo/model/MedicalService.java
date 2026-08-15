package com.vetsetgo.model;

public abstract class MedicalService {
    private String serviceName;
    private double baseFee;

    public MedicalService(String serviceName, double baseFee) {
        this.serviceName = serviceName;
        this.baseFee = baseFee;
    }

    public String getServiceName() { return serviceName; }
    public double getBaseFee() { return baseFee; }

    // Polymorphic methods to be overridden by subclasses
    public abstract double calculateCost();
    public abstract String getDetails();
}