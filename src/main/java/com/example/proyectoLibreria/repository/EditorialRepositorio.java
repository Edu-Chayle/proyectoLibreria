package com.example.proyectoLibreria.repository;

import com.example.proyectoLibreria.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EditorialRepositorio extends JpaRepository<Editorial, Long> {
    Editorial findByNombre(String nombre);
}