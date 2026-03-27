package com.example.petmanager.repository;

import com.example.petmanager.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Pet save(Pet pet);

    Optional<Pet> findById(Long id);

    List<Pet> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);
}
