package com.vetsetgo;

import com.vetsetgo.model.*;
import com.vetsetgo.utils.DateTimeUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class VetSetGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetSetGoApplication.class, args);
    }

    @Bean
    public CommandLineRunner runConsoleDemo() {
        return args -> {
            System.out.println("==================================================");
            System.out.println("          PETPULSE SYSTEM INITIALIZING            ");
            System.out.println("==================================================");

            // 1. Instantiating Subclasses (Inheritance)
            // FIX: Added mock email and phone number parameters
            PetOwner owner = new PetOwner("O101", "Alice Johnson", "pass123", "alice@email.com", "555-1234");
            Vet vet = new Vet("V202", "Dr. Bob Miller", "vetpass", "drbob@email.com", "555-9876", "VET-LICENSE-99");

            // 2. Polymorphism Demonstration
            User loggedInUser = vet;
            System.out.println("[AUTH] " + loggedInUser.displayUserPortal());

            loggedInUser = owner;
            System.out.println("[AUTH] " + loggedInUser.displayUserPortal());

            // 3. Testing Encapsulation & Rules
            // FIX: Added the breed parameter ("Domestic Shorthair")
            Pet pet = new Pet("Luna", "Feline", "Domestic Shorthair");
            try {
                pet.setAge(-1); // Triggers Encapsulation Validation
            } catch (IllegalArgumentException e) {
                System.out.println("[SECURITY] Invalid Input Caught: " + e.getMessage());
                pet.setAge(2);
                pet.setWeight(4.5);
            }
            owner.addPet(pet);

            // 4. Utilizing Utils
            LocalDateTime appointmentTime = LocalDateTime.of(2026, 8, 15, 11, 0);
            if (DateTimeUtil.isWithinClinicHours(appointmentTime)) {
                Appointment appointment = new Appointment("A-1", vet, owner, pet, appointmentTime);
                // The getStatus() will now print "PENDING" automatically due to the new Enum default
                System.out.println("[SCHEDULER] " + appointment.getStatus() + " appointment for " + pet.getName());
            }

            System.out.println("==================================================");
            System.out.println("     PETPULSE BACKEND READY FOR HTTP REQUESTS     ");
            System.out.println("==================================================");
        };
    }
}