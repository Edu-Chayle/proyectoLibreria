package com.example.proyectoLibreria.repository;

import com.example.proyectoLibreria.model.Autor;
import com.example.proyectoLibreria.model.Editorial;
import com.example.proyectoLibreria.model.Libro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class LibroRepositorioTest {
    @Autowired
    private LibroRepositorio libroRepository;

    @Test
    void findByTituloTest() {
        Libro libro = new Libro(1L, 9781400032716L, "titulo", 2023, 100,
                                50, 50, true, new Autor(1L, "autor",
                                true), new Editorial(1L, "editorial", true));

        libroRepository.save(libro);

        Libro libroEncontrado = libroRepository.findByTitulo("titulo");

        assertEquals(libro.getTitulo(), libroEncontrado.getTitulo());
    }
}