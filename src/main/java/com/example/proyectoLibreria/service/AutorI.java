package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.AutorDto;
import java.util.List;

public interface AutorI {
    List<AutorDto> findAllAutores();
    String saveAutor(AutorDto autorDto);
    AutorDto findAutor(String nombre);
    String updateAutor(Long id, AutorDto autorDto);
    String lowAutor(Long id);
    String deleteAutor(Long id);
}