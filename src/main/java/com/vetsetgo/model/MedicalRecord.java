package com.vetsetgo.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String diagnosisNotes;
    private String medicineDosages;
    private List<MedicalService> servicesRendered; // Holds any subclass of MedicalService

    public MedicalRecord() {
        this.servicesRendered = new ArrayList<>();
    }

    public MedicalRecord(String diagnosisNotes, String medicineDosages) {
        this.diagnosisNotes = diagnosisNotes;
        this.medicineDosages = medicineDosages;
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
}