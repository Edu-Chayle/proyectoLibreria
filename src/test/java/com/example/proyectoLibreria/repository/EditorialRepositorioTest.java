package com.example.proyectoLibreria.repository;

import com.example.proyectoLibreria.model.Editorial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class EditorialRepositorioTest {
    @Autowired
    private EditorialRepositorio editorialRepository;

    @Test
    void findByNombreTest() {
        Editorial editorial = new Editorial(1L, "editorial", true);

        editorialRepository.save(editorial);

        Editorial editorialEncontrada = editorialRepository.findByNombre("editorial");

        assertEquals("editorial", editorialEncontrada.getNombre());
    }
}