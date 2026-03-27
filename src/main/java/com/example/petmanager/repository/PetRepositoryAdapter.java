package com.example.petmanager.repository;

import com.example.petmanager.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PetRepositoryAdapter implements PetRepository {

    private final PetJpaRepository jpaRepository;

    public PetRepositoryAdapter(PetJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Pet save(Pet pet) {
        return jpaRepository.save(pet);
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Pet> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}
