package com.example.proyectoLibreria.controller;

import com.example.proyectoLibreria.dto.LibroDto;
import com.example.proyectoLibreria.model.Autor;
import com.example.proyectoLibreria.model.Editorial;
import com.example.proyectoLibreria.model.Libro;
import com.example.proyectoLibreria.service.LibroServicio;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
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
        LibroDto libroDto1 = new LibroDto(9781400032716L, "libro", 2023, 100,
                                          50, 50, true, new Autor(1L,
                                          "autor", true), new Editorial(1L, "editorial",
                                          true));
        LibroDto libroDto2 = new LibroDto(9780747561073L, "Mortal Kombat", 2020, 200,
                                          100, 100, false, new Autor(2L,
                                          "Jane Austen", false), new Editorial(2L,
                                          "Vintage Classics", false));

        List<LibroDto> libroDtoList = new ArrayList<>();

        libroDtoList.add(libroDto1);
        libroDtoList.add(libroDto2);

        when(libroService.findAllLibros()).thenReturn(libroDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/libros/findAll").contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void saveLibrosTest() throws Exception {
        LibroDto libroDto = new LibroDto();
        String libroDtoJson = new ObjectMapper().writeValueAsString(libroDto);

        when(libroService.saveLibro(libroDto)).thenReturn("Libro guardado exitosamente.");

        mockMvc.perform(MockMvcRequestBuilders.post("/libros/save").content(libroDtoJson)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
                                              .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void findLibroTest() throws Exception {
        LibroDto libroDto = new LibroDto();
        String libroDtoJson = new ObjectMapper().writeValueAsString(libroDto);

        when(libroService.findLibro("libro")).thenReturn(libroDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/libros/find/{titulo}", "libro")
                                              .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                                              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.content().json(libroDtoJson));;
    }

    @Test
    void updateLibroTest() throws Exception {
        LibroDto libroDto = new LibroDto();
        /*LibroDto libroDto = new LibroDto(9781400032716L, "libro", 2023, 100,
                50, 50, true, new Autor(1L,
                "autor", true), new Editorial(1L, "editorial",
                true));*/
        //Libro libro = new Libro();
        //LibroDto libroDto = mock(LibroDto.class);

        //libro.setId(1L);

        String requestJson = "{"
                + "\"isbn\": 9781400032716,"
                + "\"titulo\": \"libro\","
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

        when(libroService.updateLibro(1L, libroDto)).thenReturn(anyString());
        /*libroDto.setIsbn(1L);
        libroDto.setTitulo("libro");*/

        mockMvc.perform(MockMvcRequestBuilders.put("/libros/update/{id}", 1L).content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    void lowLibroTest() throws Exception {
        LibroDto libroDto = mock(LibroDto.class);



        when(libroService.lowLibro(1L)).thenReturn("El libro con ID " + 1L + " fue dado de baja correctamente.");

        mockMvc.perform(MockMvcRequestBuilders.put("/libros/low/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void deleteLibroTest() throws Exception {
    }
}