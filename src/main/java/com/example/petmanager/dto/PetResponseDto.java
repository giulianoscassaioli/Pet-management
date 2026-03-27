package com.example.petmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetResponseDto {

    private Long id;
    private String name;
    private String species;
    private Integer age;
    private String ownerName;
}
