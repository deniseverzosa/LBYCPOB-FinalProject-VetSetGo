package com.vetsetgo.model;

import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private Vet vet;
    private PetOwner owner;
    private Pet pet;
    private LocalDateTime timeSlot;
    private String status;

    public Appointment(String id, LocalDateTime timeSlot) {
        this.id = id;
        this.vet = vet;
        this.owner = owner;
        this.pet = pet;
        this.timeSlot = timeSlot;
        this.status = "Confirmed";
    }

    public String getId() {
        return id;
    }
    public Vet getVet() {
        return vet;
    }
    public PetOwner getOwner() {
        return owner;
    }
    public Pet getPet() {
        return pet;
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