package com.petpulse.model;

import java.time.LocalDate;

public class MedicalRecord {
    private LocalDate date;
    private String diagnosis;
    private String medications;

    public MedicalRecord(String diagnosis, String medications) {
        this.date = LocalDate.now();
        this.diagnosis = diagnosis;
        this.medications = medications;
    }
}