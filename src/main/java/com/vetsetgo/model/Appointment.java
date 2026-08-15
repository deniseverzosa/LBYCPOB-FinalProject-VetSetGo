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

    public Appointment() {
    }

    public Appointment(String id, Vet vet, PetOwner owner, Pet pet, LocalDateTime timeSlot) {
        this.id = id;
        this.vet = vet;
        this.owner = owner;
        this.pet = pet;
        this.timeSlot = timeSlot;
        this.status = AppointmentStatus.PENDING; // Defaults to pending
    }