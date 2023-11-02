package com.example.proyectoLibreria.mapper;

import com.example.proyectoLibreria.dto.AutorDto;
import com.example.proyectoLibreria.model.Autor;

public class MapperAutor {
    public static AutorDto toDto(Autor autor) {
        AutorDto autorDto = new AutorDto();

        autorDto.setNombre(autor.getNombre());
        autorDto.setAlta(autor.getAlta());

        return autorDto;
    }

    public static Autor toEntity(AutorDto autorDto) {
        Autor autor = new Autor();

        autor.setNombre(autorDto.getNombre());
        autor.setAlta(autorDto.getAlta());

        return autor;
    }
}