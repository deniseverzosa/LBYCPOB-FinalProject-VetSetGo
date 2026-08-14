package com.petpulse.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

// Abstraction & Polymorphism: Serves as the base for all authenticated entities
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PetOwner.class, name = "owner"),
        @JsonSubTypes.Type(value = Vet.class, name = "vet")
})
public abstract class User {
    @Id
    private String id;
    private String name;
    private String password;

    // Default constructor required by Spring Data JPA
    public User() {}

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}