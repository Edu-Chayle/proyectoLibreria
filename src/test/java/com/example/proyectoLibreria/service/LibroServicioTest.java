package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.LibroDto;
import com.example.proyectoLibreria.model.Libro;
import com.example.proyectoLibreria.repository.LibroRepositorio;
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
import static org.mockito.Mockito.when;

@SpringBootTest

class LibroServicioTest {
    @InjectMocks
    private LibroServicio libroService;
    @Mock
    private LibroRepositorio libroRepository;

    @Test
    void findAllLibrosTest() {
        Libro libro1 = mock(Libro.class);
        Libro libro2 = mock(Libro.class);

        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        List<LibroDto> result =  libroService.findAllLibros();

        assertEquals(2, result.size());
        assertNotNull(result);
    }

    @Test
    void saveLibroTest() {
        Libro libro = mock(Libro.class);
        LibroDto libroDto = mock(LibroDto.class);

        when(libroRepository.save(libro)).thenReturn(libro);

        String result = libroService.saveLibro(libroDto);

        assertNotNull(result);
    }

    @Test
    void findLibroTest() {
        Libro libro = mock(Libro.class);

        libro.setTitulo("titulo");

        when(libroRepository.findByTitulo("titulo")).thenReturn(libro);

        LibroDto result = libroService.findLibro("titulo");

        assertEquals(result.getTitulo(), libro.getTitulo());
        assertNotNull(result);
    }

    @Test
    void updateLibroTest() {
        Libro libro = mock(Libro.class);

        libro.setId(1L);
        libro.setTitulo("titulo");

        when(libroRepository.findById(anyLong())).thenReturn(Optional.ofNullable(libro));

        libro.setTitulo("Mortal Kombat");
        libro.setAnio(2020);

        when(libroRepository.save(libro)).thenReturn(libro);

        LibroDto libroDto = mock(LibroDto.class);

        String result = libroService.updateLibro(1L, libroDto);

        assertEquals("El libro "+ libro.getTitulo() +" fue actualizado correctamente.", result);
        assertNotNull(result);
    }

    @Test
    void lowLibroTest() {
        Libro libro = mock(Libro.class);

        libro.setId(1L);
        libro.setAlta(true);

        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        String result = libroService.lowLibro(1L);

        assertEquals("El libro con ID " + 1L + " fue dado de baja correctamente.", result);
        assertNotNull(result);
    }

    @Test
    void deleteLibroTest() {
        Libro libro = mock(Libro.class);

        libro.setId(1L);

        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        String result = libroService.deleteLibro(1L);

        assertEquals("El libro con ID " + 1L + " fue eliminado correctamente.", result);
        assertNotNull(result);
    }
}