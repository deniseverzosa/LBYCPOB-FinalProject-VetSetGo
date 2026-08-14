package com.petpulse.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

// Abstraction & Polymorphism: Serves as the base for all authenticated entities
@Entity
@Table(name = "app_user")
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

    // Polymorphic method to render portal interface dynamically
    public abstract String displayUserPortal();

    // Encapsulation
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}