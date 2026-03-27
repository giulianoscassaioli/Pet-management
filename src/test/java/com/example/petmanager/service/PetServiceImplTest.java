package com.example.petmanager.service;

import com.example.petmanager.dto.PetRequestDto;
import com.example.petmanager.dto.PetResponseDto;
import com.example.petmanager.exception.PetNotFoundException;
import com.example.petmanager.mapper.PetMapper;
import com.example.petmanager.model.Pet;
import com.example.petmanager.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetMapper petMapper;

    @InjectMocks
    private PetServiceImpl petService;

    private Pet pet;
    private PetRequestDto requestDto;
    private PetResponseDto responseDto;

    @BeforeEach
    void setUp() {
        pet = new Pet();
        pet.setId(1L);
        pet.setName("Buddy");
        pet.setSpecies("Dog");
        pet.setAge(3);

        requestDto = new PetRequestDto();
        requestDto.setName("Buddy");
        requestDto.setSpecies("Dog");
        requestDto.setAge(3);

        responseDto = new PetResponseDto();
        responseDto.setId(1L);
        responseDto.setName("Buddy");
        responseDto.setSpecies("Dog");
        responseDto.setAge(3);
    }

    @Test
    void createPet_shouldReturnResponseDto() {
        when(petMapper.toEntity(requestDto)).thenReturn(pet);
        when(petRepository.save(pet)).thenReturn(pet);
        when(petMapper.toResponseDto(pet)).thenReturn(responseDto);

        PetResponseDto result = petService.create(requestDto);

        assertNotNull(result);
        assertEquals("Buddy", result.getName());
        verify(petRepository).save(pet);
    }

    @Test
    void getPetById_shouldThrowWhenNotFound() {
        when(petRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> petService.getById(99L));
    }

    @Test
    void updatePet_shouldUpdateFields() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(pet)).thenReturn(pet);
        when(petMapper.toResponseDto(pet)).thenReturn(responseDto);

        PetResponseDto result = petService.update(1L, requestDto);

        assertNotNull(result);
        verify(petMapper).updateEntityFromDto(requestDto, pet);
        verify(petRepository).save(pet);
    }

    @Test
    void deletePet_shouldThrowWhenNotFound() {
        when(petRepository.existsById(99L)).thenReturn(false);

        assertThrows(PetNotFoundException.class, () -> petService.delete(99L));
        verify(petRepository, never()).deleteById(any());
    }
}
