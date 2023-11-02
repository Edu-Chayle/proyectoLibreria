package com.example.proyectoLibreria.repository;

import com.example.proyectoLibreria.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class AutorRepositorioTest {
    @Autowired
    private AutorRepositorio autorRepository;

    @Test
    void findByNombreTest() {
        Autor autor = new Autor(1L, "autor", true);

        autorRepository.save(autor);

        Autor autorEncontrado = autorRepository.findByNombre("autor");

        assertEquals(autor.getNombre(), autorEncontrado.getNombre());
    }
}