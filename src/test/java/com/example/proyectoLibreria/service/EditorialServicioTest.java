package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.EditorialDto;
import com.example.proyectoLibreria.model.Editorial;
import com.example.proyectoLibreria.repository.EditorialRepositorio;
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

class EditorialServicioTest {
    @Mock
    private EditorialRepositorio editorialRepositoryMock;
    @InjectMocks
    private EditorialServicio editorialService;

    @Test
    void findAllEditorialesTest() {
        Editorial editorial1 = mock(Editorial.class);
        Editorial editorial2 = mock(Editorial.class);

        when(editorialRepositoryMock.findAll()).thenReturn(Arrays.asList(editorial1, editorial2));

        List<EditorialDto> listaEditoriales = editorialService.findAllEditoriales();

        assertEquals(2, listaEditoriales.size());
        assertNotNull(listaEditoriales);

        verify(editorialRepositoryMock, times(1)).findAll();
    }

    @Test
    void saveEditorialTest() {
        Editorial editorial = mock(Editorial.class);
        EditorialDto editorialDto = mock(EditorialDto.class);

        when(editorialRepositoryMock.save(editorial)).thenReturn(editorial);

        String mensaje = editorialService.saveEditorial(editorialDto);

        assertNotNull(mensaje);
        assertEquals("Editorial guardada exitosamente.", mensaje);
    }

    @Test
    void findEditorialTest() {
        Editorial editorial = new Editorial();

        editorial.setNombre("editorial");

        when(editorialRepositoryMock.findByNombre("editorial")).thenReturn(editorial);

        EditorialDto editorialEncontrado = editorialService.findEditorial("editorial");

        assertNotNull(editorialEncontrado);
        assertEquals("editorial", editorialEncontrado.getNombre());

        verify(editorialRepositoryMock, times(1)).findByNombre("editorial");
    }

    @Test
    void updateEditorialTest() {
        Editorial editorial = new Editorial();
        EditorialDto editorialDto = new EditorialDto();

        editorialDto.setNombre("Vintage Classics");

        when(editorialRepositoryMock.findById(anyLong())).thenReturn(Optional.of(editorial));
        when(editorialRepositoryMock.save(editorial)).thenReturn(editorial);

        String mensaje = editorialService.updateEditorial(1L, editorialDto);

        assertNotNull(mensaje);
        assertEquals("La editorial "+ editorial.getNombre() +" fue actualizada correctamente.", mensaje);

        verify(editorialRepositoryMock, times(1)).findById(1L);
        verify(editorialRepositoryMock, times(1)).save(editorial);
    }

    @Test
    void lowEditorialTest() {
        Editorial editorial = mock(Editorial.class);

        when(editorialRepositoryMock.findById(1L)).thenReturn(Optional.of(editorial));
        when(editorialRepositoryMock.save(editorial)).thenReturn(editorial);

        String mensaje = editorialService.lowEditorial(1L);

        assertNotNull(mensaje);
        assertEquals("La editorial con ID " + 1L + " fue dada de baja correctamente.", mensaje);

        verify(editorialRepositoryMock, times(1)).findById(1L);
        verify(editorialRepositoryMock, times(1)).save(editorial);
    }

    @Test
    void deleteEditorialTest() {
        Editorial editorial = mock(Editorial.class);

        when(editorialRepositoryMock.findById(1L)).thenReturn(Optional.of(editorial));
        doNothing().when(editorialRepositoryMock).deleteById(1L);

        String mensaje = editorialService.deleteEditorial(1L);

        assertNotNull(mensaje);
        assertEquals("La editorial con ID " + 1L + " fue eliminada correctamente.", mensaje);

        verify(editorialRepositoryMock, times(1)).findById(1L);
        verify(editorialRepositoryMock, times(1)).deleteById(1L);
    }
}