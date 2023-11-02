package com.example.proyectoLibreria.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter

public class AutorDto {
    private String nombre;
    private Boolean alta;
}