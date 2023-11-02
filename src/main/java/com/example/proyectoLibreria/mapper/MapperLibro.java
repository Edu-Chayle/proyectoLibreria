package com.example.proyectoLibreria.mapper;

import com.example.proyectoLibreria.dto.LibroDto;
import com.example.proyectoLibreria.model.Libro;

public class MapperLibro {
    public static LibroDto toDto(Libro libro) {
        LibroDto libroDto = new LibroDto();

        libroDto.setIsbn(libro.getIsbn());
        libroDto.setTitulo(libro.getTitulo());
        libroDto.setAnio(libro.getAnio());
        libroDto.setEjemplares(libro.getEjemplares());
        libroDto.setEjemplaresPrestados(libro.getEjemplaresPrestados());
        libroDto.setEjemplaresRestantes(libro.getEjemplaresRestantes());
        libroDto.setAlta(libro.getAlta());
        libroDto.setAutor(libro.getAutor());
        libroDto.setEditorial(libro.getEditorial());

        return libroDto;
    }

    public static Libro toEntity(LibroDto libroDto) {
        Libro libro = new Libro();

        libro.setIsbn(libroDto.getIsbn());
        libro.setTitulo(libroDto.getTitulo());
        libro.setAnio(libroDto.getAnio());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setEjemplaresPrestados(libroDto.getEjemplaresPrestados());
        libro.setEjemplaresRestantes(libroDto.getEjemplaresRestantes());
        libro.setAlta(libroDto.getAlta());
        libro.setAutor(libroDto.getAutor());
        libro.setEditorial(libroDto.getEditorial());

        return libro;
    }
}