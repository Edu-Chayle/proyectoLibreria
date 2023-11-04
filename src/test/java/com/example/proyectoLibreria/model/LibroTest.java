package com.example.proyectoLibreria.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibroTest {
    Libro libro = new Libro(1L, 9781400032716L, "libro", 2023, 100, 50,
                            50, true, new Autor(1L, "autor", true), new
                            Editorial(1L, "editorial", true));

    @Test
    void getIdTest() {
        assertEquals(1L, libro.getId());
    }

    @Test
    void getIsbnTest() {
        assertEquals(9781400032716L, libro.getIsbn());
    }

    @Test
    void getTituloTest() {
        assertEquals("libro", libro.getTitulo());
    }

    @Test
    void getAnioTest() {
        assertEquals(2023, libro.getAnio());
    }

    @Test
    void getEjemplaresTest() {
        assertEquals(100, libro.getEjemplares());
    }

    @Test
    void getEjemplaresPrestadosTest() {
        assertEquals(50, libro.getEjemplaresPrestados());
    }

    @Test
    void getEjemplaresRestantesTest() {
        assertEquals(50, libro.getEjemplaresRestantes());
    }

    @Test
    void getAltaTest() {
        assertEquals(true, libro.getAlta());
    }

    @Test
    void getAutorTest() {
        assertEquals(1L, libro.getAutor().getId());
        assertEquals("autor", libro.getAutor().getNombre());
        assertEquals(true, libro.getAutor().getAlta());
    }

    @Test
    void getEditorialTest() {
        assertEquals(1L, libro.getEditorial().getId());
        assertEquals("editorial", libro.getEditorial().getNombre());
        assertEquals(true, libro.getEditorial().getAlta());
    }

    @Test
    void setIdTest() {
        libro.setId(2L);

        assertEquals(2L, libro.getId());
    }

    @Test
    void setIsbnTest() {
        libro.setIsbn(9780747561073L);

        assertEquals(9780747561073L, libro.getIsbn());
    }

    @Test
    void setTituloTest() {
        libro.setTitulo("Mortal Kombat");

        assertEquals("Mortal Kombat", libro.getTitulo());
    }

    @Test
    void setAnioTest() {
        libro.setAnio(2020);

        assertEquals(2020, libro.getAnio());
    }

    @Test
    void setEjemplaresTest() {
        libro.setEjemplares(200);

        assertEquals(200, libro.getEjemplares());
    }

    @Test
    void setEjemplaresPrestadosTest() {
        libro.setEjemplaresPrestados(100);

        assertEquals(100, libro.getEjemplaresPrestados());
    }

    @Test
    void setEjemplaresRestantesTest() {
        libro.setEjemplaresRestantes(100);

        assertEquals(100, libro.getEjemplaresRestantes());
    }

    @Test
    void setAltaTest() {
        libro.setAlta(false);

        assertEquals(false, libro.getAlta());
    }

    @Test
    void setAutorTest() {
        Autor autor = new Autor(2L, "Jane Austen", false);

        libro.setAutor(autor);

        assertEquals(autor, libro.getAutor());
    }

    @Test
    void setEditorialTest() {
        Editorial editorial = new Editorial(2L, "Vintage Classics", false);

        libro.setEditorial(editorial);

        assertEquals(editorial, libro.getEditorial());
    }
}