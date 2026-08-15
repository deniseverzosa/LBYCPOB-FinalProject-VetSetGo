package com.vetsetgo.model;

// Imported LocalDateTime
import java.time.LocalDateTime;

public class MedicalRecord {
    private LocalDateTime timestamp;
    private String diagnosisNotes;
    private String medicineDosages;
    private String vitalSigns;

    //Constructor now accepts the new fields and sets current time
    public MedicalRecord(String diagnosisNotes, String medicineDosages, String vitalSigns) {
        this.timestamp = LocalDateTime.now();
        this.diagnosisNotes = diagnosisNotes;
        this.medicineDosages = medicineDosages;
        this.vitalSigns = vitalSigns;
    }

    // Updated Getters and Setters for all fields
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getDiagnosisNotes() { return diagnosisNotes; }
    public void setDiagnosisNotes(String diagnosisNotes) { this.diagnosisNotes = diagnosisNotes; }

    public String getMedicineDosages() { return medicineDosages; }
    public void setMedicineDosages(String medicineDosages) { this.medicineDosages = medicineDosages; }

    public String getVitalSigns() { return vitalSigns; }
    public void setVitalSigns(String vitalSigns) { this.vitalSigns = vitalSigns; }
}