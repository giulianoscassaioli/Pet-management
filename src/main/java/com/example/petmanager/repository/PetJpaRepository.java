package com.example.petmanager.repository;

import com.example.petmanager.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetJpaRepository extends JpaRepository<Pet, Long> {
}
