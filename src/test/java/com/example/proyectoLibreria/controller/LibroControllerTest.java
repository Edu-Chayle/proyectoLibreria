package com.example.proyectoLibreria.controller;

import com.example.proyectoLibreria.dto.LibroDto;
import com.example.proyectoLibreria.model.Autor;
import com.example.proyectoLibreria.model.Editorial;
import com.example.proyectoLibreria.service.LibroServicio;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(LibroController.class)

class LibroControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LibroServicio libroService;

    @Test
    void findAllLibrosTest() throws Exception {
        LibroDto libroDto1 = new LibroDto();
        LibroDto libroDto2 = new LibroDto();

        libroDto1.setIsbn(9781400032716L);
        libroDto1.setTitulo("titulo");
        libroDto1.setAnio(2023);
        libroDto1.setEjemplares(100);
        libroDto1.setEjemplaresPrestados(50);
        libroDto1.setEjemplaresRestantes(50);
        libroDto1.setAlta(true);
        libroDto1.setAutor(new Autor(1L, "autor", true));
        libroDto1.setEditorial(new Editorial(1L, "editorial", true));

        libroDto1.setIsbn(9780747561073L);
        libroDto1.setTitulo("Mortal Kombat");
        libroDto1.setAnio(2020);
        libroDto1.setEjemplares(200);
        libroDto1.setEjemplaresPrestados(100);
        libroDto1.setEjemplaresRestantes(100);
        libroDto1.setAlta(false);
        libroDto1.setAutor(new Autor(2L, "Jane Austen", false));
        libroDto1.setEditorial(new Editorial(2L, "Vintage Classics", false));

        List<LibroDto> libroDtoList = new ArrayList<>();

        libroDtoList.add(libroDto1);
        libroDtoList.add(libroDto2);

        when(libroService.findAllLibros()).thenReturn(libroDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/libros/findAll").contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void saveLibrosTest() throws Exception {
        LibroDto libroDto = mock(LibroDto.class);

        String requestJson = "{"
                + "\"isbn\": 9781400032716,"
                + "\"titulo\": \"titulo\","
                + "\"anio\": 2023,"
                + "\"ejemplares\": 100,"
                + "\"ejemplaresPrestados\": 50,"
                + "\"ejemplaresRestantes\": 50,"
                + "\"alta\": true,"
                + "\"autor\": {"
                + "    \"nombre\": \"autor\","
                + "    \"alta\": true"
                + "},"
                + "\"editorial\": {"
                + "    \"nombre\": \"editorial\","
                + "    \"alta\": true"
                + "}"
                + "}";

        when(libroService.saveLibro(libroDto)).thenReturn(anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/libros/save").content(requestJson)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findLibroTest() throws Exception {
        LibroDto libroDto = new LibroDto();

        libroDto.setTitulo("titulo");

        when(libroService.findLibro(anyString())).thenReturn(libroDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/libros/find/{titulo}", "titulo")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void updateLibroTest() throws Exception {
    }

    @Test
    void lowLibroTest() throws Exception {
    }

    @Test
    void deleteLibroTest() throws Exception {
    }
}