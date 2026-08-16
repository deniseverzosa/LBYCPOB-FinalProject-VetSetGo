package com.vetsetgo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // Marks this class as a database table
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosisNotes;
    private String medicineDosages;
    private String vitalSigns;

    // Added the missing timestamp field that the HTML templates are looking for
    private LocalDateTime timestamp;

    @Transient
    private List<MedicalService> servicesRendered;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public MedicalRecord() {
        this.servicesRendered = new ArrayList<>();
        this.timestamp = LocalDateTime.now(); // Automatically logs the exact time of creation
    }

    public MedicalRecord(String diagnosisNotes, String medicineDosages, String vitalSigns) {
        this.diagnosisNotes = diagnosisNotes;
        this.medicineDosages = medicineDosages;
        this.vitalSigns = vitalSigns;
        this.servicesRendered = new ArrayList<>();
        this.timestamp = LocalDateTime.now(); // Automatically logs the exact time of creation
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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}