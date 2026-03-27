package com.example.petmanager.service;

import com.example.petmanager.dto.PetRequestDto;
import com.example.petmanager.dto.PetResponseDto;
import com.example.petmanager.exception.PetNotFoundException;
import com.example.petmanager.mapper.PetMapper;
import com.example.petmanager.model.Pet;
import com.example.petmanager.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetServiceImpl(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    @Override
    public PetResponseDto create(PetRequestDto dto) {
        Pet pet = petMapper.toEntity(dto);
        return petMapper.toResponseDto(petRepository.save(pet));
    }

    @Override
    public PetResponseDto getById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(id));
        return petMapper.toResponseDto(pet);
    }

    @Override
    public List<PetResponseDto> getAll() {
        return petRepository.findAll().stream()
                .map(petMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PetResponseDto update(Long id, PetRequestDto dto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(id));
        petMapper.updateEntityFromDto(dto, pet);
        return petMapper.toResponseDto(petRepository.save(pet));
    }

    @Override
    public void delete(Long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException(id);
        }
        petRepository.deleteById(id);
    }
}
