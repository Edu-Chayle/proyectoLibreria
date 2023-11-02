package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.AutorDto;
import com.example.proyectoLibreria.model.Autor;
import com.example.proyectoLibreria.model.Libro;
import com.example.proyectoLibreria.repository.AutorRepositorio;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest

class AutorServicioTest {
    @InjectMocks
    private AutorServicio autorService;
    @Mock
    private AutorRepositorio autorRepository;

    @Test
    void findAllAutoresTest() {
        Autor autor1 = mock(Autor.class);
        Autor autor2 = mock(Autor.class);

        when(autorRepository.findAll()).thenReturn(Arrays.asList(autor1, autor2));

        List<AutorDto> result =  autorService.findAllAutores();

        assertEquals(2, result.size());
        assertNotNull(result);
    }

    @Test
    void saveAutorTest() {
        Autor autor = mock(Autor.class);
        AutorDto autorDto = mock(AutorDto.class);

        when(autorRepository.save(autor)).thenReturn(autor);

        String result = autorService.saveAutor(autorDto);

        assertNotNull(result);
    }

    @Test
    void findAutorTest() {
        Autor autor = mock(Autor.class);

        autor.setNombre("autor");

        when(autorRepository.findByNombre("autor")).thenReturn(autor);

        AutorDto result = autorService.findAutor("autor");

        assertEquals(result.getNombre(), autor.getNombre());
        assertNotNull(result);
    }

    @Test
    void updateAutorTest() {
        Autor autor = mock(Autor.class);

        autor.setId(1L);
        autor.setNombre("autor");

        when(autorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(autor));

        autor.setNombre("Jane Austen");
        autor.setAlta(true);

        when(autorRepository.save(autor)).thenReturn(autor);

        AutorDto autorDto = mock(AutorDto.class);

        String result = autorService.updateAutor(1L, autorDto);

        assertEquals("El autor "+ autor.getNombre() +" fue actualizado correctamente.", result);
        assertNotNull(result);
    }

    @Test
    void lowAutorTest() {
        Autor autor = mock(Autor.class);

        autor.setId(1L);
        autor.setAlta(true);

        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));

        String result = autorService.lowAutor(1L);

        assertEquals("El autor con ID " + 1L + " fue dado de baja correctamente.", result);
        assertNotNull(result);
    }

    @Test
    void deleteAutorTest() {
        Autor autor = mock(Autor.class);

        autor.setId(1L);

        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));

        String result = autorService.deleteAutor(1L);

        assertEquals("El autor con ID " + 1L + " fue eliminado correctamente.", result);
        assertNotNull(result);
    }
}