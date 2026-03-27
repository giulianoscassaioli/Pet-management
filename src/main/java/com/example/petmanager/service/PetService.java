package com.example.petmanager.service;

import com.example.petmanager.dto.PetRequestDto;
import com.example.petmanager.dto.PetResponseDto;

import java.util.List;

public interface PetService {

    PetResponseDto create(PetRequestDto dto);

    PetResponseDto getById(Long id);

    List<PetResponseDto> getAll();

    PetResponseDto update(Long id, PetRequestDto dto);

    void delete(Long id);
}
