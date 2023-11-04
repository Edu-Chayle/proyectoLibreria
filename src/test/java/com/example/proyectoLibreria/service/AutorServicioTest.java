package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.AutorDto;
import com.example.proyectoLibreria.model.Autor;
import com.example.proyectoLibreria.repository.AutorRepositorio;
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

class AutorServicioTest {
    @Mock
    private AutorRepositorio autorRepositoryMock;
    @InjectMocks
    private AutorServicio autorService;

    @Test
    void findAllAutoresTest() {
        Autor autor1 = mock(Autor.class);
        Autor autor2 = mock(Autor.class);

        when(autorRepositoryMock.findAll()).thenReturn(Arrays.asList(autor1, autor2));

        List<AutorDto> listaAutores = autorService.findAllAutores();

        assertNotNull(listaAutores);
        assertEquals(2, listaAutores.size());

        verify(autorRepositoryMock, times(1)).findAll();
    }

    @Test
    void saveAutorTest() {
        Autor autor = mock(Autor.class);
        AutorDto autorDto = mock(AutorDto.class);

        when(autorRepositoryMock.save(autor)).thenReturn(autor);

        String mensaje = autorService.saveAutor(autorDto);

        assertNotNull(mensaje);
        assertEquals("Autor guardado exitosamente.", mensaje);
    }

    @Test
    void findAutorTest() {
        Autor autor = new Autor();

        autor.setNombre("autor");

        when(autorRepositoryMock.findByNombre("autor")).thenReturn(autor);

        AutorDto autorEncontrado = autorService.findAutor("autor");

        assertNotNull(autorEncontrado);
        assertEquals("autor", autorEncontrado.getNombre());

        verify(autorRepositoryMock, times(1)).findByNombre("autor");
    }

    @Test
    void updateAutorTest() {
        Autor autor = new Autor();
        AutorDto autorDto = new AutorDto();

        autorDto.setNombre("Jane Austen");

        when(autorRepositoryMock.findById(1L)).thenReturn(Optional.of(autor));
        when(autorRepositoryMock.save(autor)).thenReturn(autor);

        String mensaje = autorService.updateAutor(1L, autorDto);

        assertNotNull(mensaje);
        assertEquals("El autor "+ autor.getNombre() +" fue actualizado correctamente.", mensaje);

        verify(autorRepositoryMock, times(1)).findById(1L);
        verify(autorRepositoryMock, times(1)).save(autor);
    }

    @Test
    void lowAutorTest() {
        Autor autor = mock(Autor.class);

        when(autorRepositoryMock.findById(1L)).thenReturn(Optional.of(autor));
        when(autorRepositoryMock.save(autor)).thenReturn(autor);

        String mensaje = autorService.lowAutor(1L);

        assertNotNull(mensaje);
        assertEquals("El autor con ID " + 1L + " fue dado de baja correctamente.", mensaje);

        verify(autorRepositoryMock, times(1)).findById(1L);
        verify(autorRepositoryMock, times(1)).save(autor);
    }

    @Test
    void deleteAutorTest() {
        Autor autor = mock(Autor.class);

        when(autorRepositoryMock.findById(1L)).thenReturn(Optional.of(autor));
        doNothing().when(autorRepositoryMock).deleteById(1L);

        String mensaje = autorService.deleteAutor(1L);

        assertNotNull(mensaje);
        assertEquals("El autor con ID " + 1L + " fue eliminado correctamente.", mensaje);

        verify(autorRepositoryMock, times(1)).findById(1L);
        verify(autorRepositoryMock, times(1)).deleteById(1L);
    }
}