package com.example.petmanager;

import com.example.petmanager.model.Pet;
import com.example.petmanager.repository.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PetRepository petRepository;

    public DataLoader(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) {
        Pet buddy = new Pet();
        buddy.setName("Buddy");
        buddy.setSpecies("Dog");
        buddy.setAge(3);
        buddy.setOwnerName("John Smith");

        Pet whiskers = new Pet();
        whiskers.setName("Whiskers");
        whiskers.setSpecies("Cat");
        whiskers.setAge(5);
        whiskers.setOwnerName("Jane Doe");

        Pet cotton = new Pet();
        cotton.setName("Cotton");
        cotton.setSpecies("Rabbit");
        cotton.setAge(1);

        petRepository.save(buddy);
        petRepository.save(whiskers);
        petRepository.save(cotton);
    }
}
