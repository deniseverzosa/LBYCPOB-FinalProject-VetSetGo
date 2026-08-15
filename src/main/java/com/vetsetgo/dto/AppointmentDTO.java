package com.vetsetgo.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private String id;
    private String petName;
    private String vetName;
    private String vetId;
    private LocalDateTime timeSlot;

    public AppointmentDTO() {}

    public AppointmentDTO(String id, String petName, String vetName, LocalDateTime timeSlot) {
        this.id = id;
        this.petName = petName;
        this.vetName = vetName;
        this.vetId = vetId;
        this.timeSlot = timeSlot;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public String getVetName() { return vetName; }
    public void setVetName(String vetName) { this.vetName = vetName; }

    public String getVetId() { return vetId; }
    public void setVetId(String vetId) { this.vetId = vetId; }

    public LocalDateTime getTimeSlot() { return timeSlot; }
    public void setTimeSlot(LocalDateTime timeSlot) { this.timeSlot = timeSlot; }
}