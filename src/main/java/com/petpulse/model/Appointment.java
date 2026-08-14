package com.petpulse.model;

import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private LocalDateTime timeSlot;
    private String status;

    public Appointment(String id, LocalDateTime timeSlot) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.status = "Confirmed";
    }

    public String getId() {
        return id;
    }
    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}