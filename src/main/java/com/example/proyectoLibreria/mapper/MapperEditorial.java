package com.example.proyectoLibreria.mapper;

import com.example.proyectoLibreria.dto.EditorialDto;
import com.example.proyectoLibreria.model.Editorial;

public class MapperEditorial {
    public static EditorialDto toDto(Editorial editorial) {
        EditorialDto editorialDto = new EditorialDto();

        editorialDto.setNombre(editorial.getNombre());
        editorialDto.setAlta(editorial.getAlta());

        return editorialDto;
    }

    public static Editorial toEntity(EditorialDto editorialDto) {
        Editorial editorial = new Editorial();

        editorial.setNombre(editorialDto.getNombre());
        editorial.setAlta(editorialDto.getAlta());

        return editorial;
    }
}