package com.example.petmanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Species is required")
    private String species;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private Integer age;

    private String ownerName;
}
