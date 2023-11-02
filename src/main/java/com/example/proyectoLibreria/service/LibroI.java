package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.LibroDto;
import java.util.List;

public interface LibroI {
    List<LibroDto> findAllLibros();
    String saveLibro(LibroDto libroDto);
    LibroDto findLibro(String titulo);
    String updateLibro(Long id, LibroDto libroDto);
    String lowLibro(Long id);
    String deleteLibro(Long id);
}