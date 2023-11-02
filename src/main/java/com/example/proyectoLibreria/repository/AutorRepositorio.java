package com.example.proyectoLibreria.repository;

import com.example.proyectoLibreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);
}