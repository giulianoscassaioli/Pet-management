package com.example.petmanager.controller;

import com.example.petmanager.dto.PetRequestDto;
import com.example.petmanager.dto.PetResponseDto;
import com.example.petmanager.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetResponseDto> create(@Valid @RequestBody PetRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDto>> getAll() {
        return ResponseEntity.ok(petService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDto> update(@PathVariable Long id, @Valid @RequestBody PetRequestDto dto) {
        return ResponseEntity.ok(petService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
