package com.example.proyectoLibreria.dto;

import com.example.proyectoLibreria.model.Autor;
import com.example.proyectoLibreria.model.Editorial;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter

public class LibroDto {
    private Long isbn;
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;
    private Autor autor;
    private Editorial editorial;
}