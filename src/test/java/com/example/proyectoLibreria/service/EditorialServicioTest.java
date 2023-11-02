package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.EditorialDto;
import com.example.proyectoLibreria.model.Autor;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest

class EditorialServicioTest {
    @InjectMocks
    private EditorialServicio editorialService;
    @Mock
    private EditorialRepositorio editorialRepository;

    @Test
    void findAllEditorialesTest() {
        Editorial editorial1 = mock(Editorial.class);
        Editorial editorial2 = mock(Editorial.class);

        when(editorialRepository.findAll()).thenReturn(Arrays.asList(editorial1, editorial2));

        List<EditorialDto> result =  editorialService.findAllEditoriales();

        assertEquals(2, result.size());
        assertNotNull(result);
    }

    @Test
    void saveEditorialTest() {
        Editorial editorial = mock(Editorial.class);
        EditorialDto editorialDto = mock(EditorialDto.class);

        when(editorialRepository.save(editorial)).thenReturn(editorial);

        String result = editorialService.saveEditorial(editorialDto);

        assertNotNull(result);
    }

    @Test
    void findEditorialTest() {
        Editorial editorial = mock(Editorial.class);

        editorial.setNombre("editorial");

        when(editorialRepository.findByNombre("editorial")).thenReturn(editorial);

        EditorialDto result = editorialService.findEditorial("editorial");

        assertEquals(result.getNombre(), editorial.getNombre());
        assertNotNull(result);
    }

    @Test
    void updateEditorialTest() {
        Editorial editorial = mock(Editorial.class);

        editorial.setId(1L);
        editorial.setNombre("editorial");

        when(editorialRepository.findById(anyLong())).thenReturn(Optional.ofNullable(editorial));

        editorial.setNombre("Vintage Classics");
        editorial.setAlta(true);

        when(editorialRepository.save(editorial)).thenReturn(editorial);

        EditorialDto editorialDto = mock(EditorialDto.class);

        String result = editorialService.updateEditorial(1L, editorialDto);

        assertEquals("La editorial "+ editorial.getNombre() +" fue actualizada correctamente.", result);
        assertNotNull(result);
    }

    @Test
    void lowEditorialTest() {
        Editorial editorial = mock(Editorial.class);

        editorial.setId(1L);
        editorial.setAlta(true);

        when(editorialRepository.findById(1L)).thenReturn(Optional.of(editorial));

        String result = editorialService.lowEditorial(1L);

        assertEquals("La editorial con ID " + 1L + " fue dada de baja correctamente.", result);
        assertNotNull(result);
    }

    @Test
    void deleteEditorialTest() {
        Editorial editorial = mock(Editorial.class);

        editorial.setId(1L);

        when(editorialRepository.findById(1L)).thenReturn(Optional.of(editorial));

        String result = editorialService.deleteEditorial(1L);

        assertEquals("La editorial con ID " + 1L + " fue eliminada correctamente.", result);
        assertNotNull(result);
    }
}