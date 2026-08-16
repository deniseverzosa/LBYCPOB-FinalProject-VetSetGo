package com.vetsetgo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    private String id;

    @ManyToOne
    private Vet vet;

    @ManyToOne
    private PetOwner owner;

    @ManyToOne
    private Pet pet;

    private LocalDateTime timeSlot;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String serviceType;
    private Double servicePrice;

    public Appointment() {
    }

    public Appointment(String id, Vet vet, PetOwner owner, Pet pet, LocalDateTime timeSlot, String serviceType, Double servicePrice) {
        this.id = id;
        this.vet = vet;
        this.owner = owner;
        this.pet = pet;
        this.timeSlot = timeSlot;
        this.status = AppointmentStatus.PENDING; // Defaults to pending
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public PetOwner getOwner() {
        return owner;
    }

    public void setOwner(PetOwner owner) {
        this.owner = owner;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(Double servicePrice) {
        this.servicePrice = servicePrice;
    }

}