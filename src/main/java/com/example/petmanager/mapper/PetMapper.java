package com.example.petmanager.mapper;

import com.example.petmanager.dto.PetRequestDto;
import com.example.petmanager.dto.PetResponseDto;
import com.example.petmanager.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PetMapper {

    Pet toEntity(PetRequestDto dto);

    PetResponseDto toResponseDto(Pet pet);

    void updateEntityFromDto(PetRequestDto dto, @MappingTarget Pet pet);
}
