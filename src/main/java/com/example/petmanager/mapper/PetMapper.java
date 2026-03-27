package com.example.petmanager.mapper;

import com.example.petmanager.dto.PetRequestDto;
import com.example.petmanager.dto.PetResponseDto;
import com.example.petmanager.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    public Pet toEntity(PetRequestDto dto) {
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setSpecies(dto.getSpecies());
        pet.setAge(dto.getAge());
        pet.setOwnerName(dto.getOwnerName());
        return pet;
    }

    public PetResponseDto toResponseDto(Pet pet) {
        PetResponseDto dto = new PetResponseDto();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setSpecies(pet.getSpecies());
        dto.setAge(pet.getAge());
        dto.setOwnerName(pet.getOwnerName());
        return dto;
    }

    public void updateEntityFromDto(PetRequestDto dto, Pet pet) {
        pet.setName(dto.getName());
        pet.setSpecies(dto.getSpecies());
        pet.setAge(dto.getAge());
        pet.setOwnerName(dto.getOwnerName());
    }
}
