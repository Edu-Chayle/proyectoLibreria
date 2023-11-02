package com.example.proyectoLibreria.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutorTest {
    Autor autor = new Autor(1L, "autor", true);

    @Test
    void getIdTest() {
        assertEquals(1L, autor.getId());
    }

    @Test
    void getNombreTest() {
        assertEquals("autor", autor.getNombre());
    }

    @Test
    void getAltaTest() {
        assertEquals(true, autor.getAlta());
    }

    @Test
    void setIdTest() {
        autor.setId(2L);

        assertEquals(2L, autor.getId());
    }

    @Test
    void setNombreTest() {
        autor.setNombre("Jane Austen");

        assertEquals("Jane Austen", autor.getNombre());
    }

    @Test
    void setAltaTest() {
        autor.setAlta(false);

        assertEquals(false, autor.getAlta());
    }
}