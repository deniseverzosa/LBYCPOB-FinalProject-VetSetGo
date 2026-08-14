package com.petpulse.dto;

public class PetDTO {
    private String name;
    private String species;
    private int age;
    private double weight;

    public PetDTO() {}

    public PetDTO(String name, String species, int age, double weight) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.weight = weight;
    }
}
