package com.petpulse.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private String id;
    private String petName;
    private String vetName;
    private LocalDateTime timeSlot;

    public AppointmentDTO() {}

    public AppointmentDTO(String id, String petName, String vetName, LocalDateTime timeSlot) {
        this.id = id;
        this.petName = petName;
        this.vetName = vetName;
        this.timeSlot = timeSlot;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
}