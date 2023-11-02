package com.example.proyectoLibreria.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialTest {
    Editorial editorial = new Editorial(1L, "editorial", true);

    @Test
    void getIdTest() {
        assertEquals(1L, editorial.getId());
    }

    @Test
    void getNombreTest() {
        assertEquals("editorial", editorial.getNombre());
    }

    @Test
    void getAltaTest() {
        assertEquals(true, editorial.getAlta());
    }

    @Test
    void setIdTest() {
        editorial.setId(2L);

        assertEquals(2L, editorial.getId());
    }

    @Test
    void setNombreTest() {
        editorial.setNombre("Vintage Classics");

        assertEquals("Vintage Classics", editorial.getNombre());
    }

    @Test
    void setAltaTest() {
        editorial.setAlta(false);

        assertEquals(false, editorial.getAlta());
    }
}