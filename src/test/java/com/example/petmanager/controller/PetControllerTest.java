package com.example.petmanager.controller;

import com.example.petmanager.exception.PetNotFoundException;
import com.example.petmanager.service.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.petmanager.dto.PetRequestDto;
import com.example.petmanager.dto.PetResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PetService petService;

    private PetRequestDto buildRequest(String name, String species) {
        PetRequestDto dto = new PetRequestDto();
        dto.setName(name);
        dto.setSpecies(species);
        dto.setAge(2);
        return dto;
    }

    private PetResponseDto buildResponse(Long id, String name, String species) {
        PetResponseDto dto = new PetResponseDto();
        dto.setId(id);
        dto.setName(name);
        dto.setSpecies(species);
        dto.setAge(2);
        return dto;
    }

    @Test
    void createPet_shouldReturn201() throws Exception {
        PetRequestDto request = buildRequest("Buddy", "Dog");
        PetResponseDto response = buildResponse(1L, "Buddy", "Dog");

        when(petService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void getAllPets_shouldReturn200() throws Exception {
        when(petService.getAll()).thenReturn(List.of(buildResponse(1L, "Buddy", "Dog")));

        mockMvc.perform(get("/api/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getPetById_shouldReturn200() throws Exception {
        when(petService.getById(1L)).thenReturn(buildResponse(1L, "Buddy", "Dog"));

        mockMvc.perform(get("/api/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void getPetById_shouldReturn404WhenNotFound() throws Exception {
        when(petService.getById(99L)).thenThrow(new PetNotFoundException(99L));

        mockMvc.perform(get("/api/pets/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePet_shouldReturn200() throws Exception {
        PetRequestDto request = buildRequest("Max", "Cat");
        PetResponseDto response = buildResponse(1L, "Max", "Cat");

        when(petService.update(eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/api/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Max"));
    }

    @Test
    void deletePet_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/pets/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void createPet_shouldReturn400WhenNameIsMissing() throws Exception {
        PetRequestDto request = new PetRequestDto();
        request.setSpecies("Dog");

        mockMvc.perform(post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").exists());
    }
}
