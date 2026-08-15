package com.vetsetgo.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String diagnosisNotes;
    private String medicineDosages;
    private String vitalSigns;
    private List<MedicalService> servicesRendered;

    // FIX: Added the missing Pet reference to link back to the owner pet
    private Pet pet;

    public MedicalRecord() {
        this.servicesRendered = new ArrayList<>();
    }

    public MedicalRecord(String diagnosisNotes, String medicineDosages, String vitalSigns) {
        this.diagnosisNotes = diagnosisNotes;
        this.medicineDosages = medicineDosages;
        this.vitalSigns = vitalSigns;
        this.servicesRendered = new ArrayList<>();
    }

    // Helper method to add services dynamically
    public void addService(MedicalService service) {
        this.servicesRendered.add(service);
    }

    // Polymorphism in action: dynamic calculation regardless of service subclass type
    public double calculateTotalCost() {
        double total = 0;
        for (MedicalService service : servicesRendered) {
            total += service.calculateCost(); // Invokes overridden method per subclass
        }
        return total;
    }

    // Getters & Setters
    public List<MedicalService> getServicesRendered() { return servicesRendered; }
    public void setServicesRendered(List<MedicalService> servicesRendered) { this.servicesRendered = servicesRendered; }

    public String getDiagnosisNotes() { return diagnosisNotes; }
    public void setDiagnosisNotes(String diagnosisNotes) { this.diagnosisNotes = diagnosisNotes; }

    public String getMedicineDosages() { return medicineDosages; }
    public void setMedicineDosages(String medicineDosages) { this.medicineDosages = medicineDosages; }

    public String getVitalSigns() { return vitalSigns; }
    public void setVitalSigns(String vitalSigns) { this.vitalSigns = vitalSigns; }

    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }
}