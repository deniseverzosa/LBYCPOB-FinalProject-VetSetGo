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

    public Appointment() {}