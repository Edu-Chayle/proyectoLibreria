package com.example.proyectoLibreria.repository;

import com.example.proyectoLibreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    Libro findByTitulo(String titulo);
}