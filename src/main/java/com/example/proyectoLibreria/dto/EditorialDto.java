package com.example.proyectoLibreria.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter

public class EditorialDto {
    private String nombre;
    private Boolean alta;
}