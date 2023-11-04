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
import static org.mockito.Mockito.*;

@SpringBootTest

class LibroServicioTest {
    @Mock
    private LibroRepositorio libroRepositoryMock;
    @InjectMocks
    private LibroServicio libroService;

    @Test
    void findAllLibrosTest() {
        Libro libro1 = mock(Libro.class);
        Libro libro2 = mock(Libro.class);

        when(libroRepositoryMock.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        List<LibroDto> listaLibros = libroService.findAllLibros();

        assertNotNull(listaLibros);
        assertEquals(2, listaLibros.size());

        verify(libroRepositoryMock, times(1)).findAll();
    }

    @Test
    void saveLibroTest() {
        Libro libro = mock(Libro.class);
        LibroDto libroDto = mock(LibroDto.class);

        when(libroRepositoryMock.save(libro)).thenReturn(libro);

        String mensaje = libroService.saveLibro(libroDto);

        assertNotNull(mensaje);
        assertEquals("Libro guardado exitosamente.", mensaje);
    }

    @Test
    void findLibroTest() {
        Libro libro = new Libro();

        libro.setTitulo("libro");

        when(libroRepositoryMock.findByTitulo("libro")).thenReturn(libro);

        LibroDto libroEncontrado = libroService.findLibro("libro");

        assertNotNull(libroEncontrado);
        assertEquals("libro", libroEncontrado.getTitulo());

        verify(libroRepositoryMock, times(1)).findByTitulo("libro");
    }

    @Test
    void updateLibroTest() {
        Libro libro = new Libro();
        LibroDto libroDto = new LibroDto();

        libroDto.setTitulo("Mortal Kombat");

        when(libroRepositoryMock.findById(1L)).thenReturn(Optional.of(libro));
        when(libroRepositoryMock.save(libro)).thenReturn(libro);

        String mensaje = libroService.updateLibro(1L, libroDto);

        assertNotNull(mensaje);
        assertEquals("El libro "+ libro.getTitulo() +" fue actualizado correctamente.", mensaje);

        verify(libroRepositoryMock, times(1)).findById(1L);
        verify(libroRepositoryMock, times(1)).save(libro);
    }

    @Test
    void lowLibroTest() {
        Libro libro = mock(Libro.class);

        when(libroRepositoryMock.findById(1L)).thenReturn(Optional.of(libro));
        when(libroRepositoryMock.save(libro)).thenReturn(libro);

        String mensaje = libroService.lowLibro(1L);

        assertNotNull(mensaje);
        assertEquals("El libro con ID " + 1L + " fue dado de baja correctamente.", mensaje);

        verify(libroRepositoryMock, times(1)).findById(1L);
        verify(libroRepositoryMock, times(1)).save(libro);
    }

    @Test
    void deleteLibroTest() {
        Libro libro = mock(Libro.class);

        when(libroRepositoryMock.findById(1L)).thenReturn(Optional.of(libro));
        doNothing().when(libroRepositoryMock).deleteById(1L);

        String mensaje = libroService.deleteLibro(1L);

        assertNotNull(mensaje);
        assertEquals("El libro con ID " + 1L + " fue eliminado correctamente.", mensaje);

        verify(libroRepositoryMock, times(1)).findById(1L);
        verify(libroRepositoryMock, times(1)).deleteById(1L);
    }
}