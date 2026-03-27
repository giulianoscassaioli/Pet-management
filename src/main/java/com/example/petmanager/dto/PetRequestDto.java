package com.example.petmanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    @Min(0)
    private Integer age;

    private String ownerName;
}
