package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.EditorialDto;
import com.example.proyectoLibreria.exceptions.EditorialNoEncontradaException;
import com.example.proyectoLibreria.mapper.MapperEditorial;
import com.example.proyectoLibreria.model.Editorial;
import com.example.proyectoLibreria.repository.EditorialRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class EditorialServicio implements EditorialI {
    private final EditorialRepositorio editorialRepository;

    @Override
    public List<EditorialDto> findAllEditoriales() {
        List<EditorialDto> listaEditoriales = editorialRepository.findAll().stream().map(MapperEditorial::toDto)
                                                                                    .collect(Collectors.toList());

        return listaEditoriales;
    }

    @Override
    public String saveEditorial(EditorialDto editorialDto) {
        Editorial editorialEntity = MapperEditorial.toEntity(editorialDto);

        editorialRepository.save(editorialEntity);

        return "Editorial guardada exitosamente.";
    }

    @Override
    public EditorialDto findEditorial(String nombre) {
        Editorial editorialEntity = editorialRepository.findByNombre(nombre);

        if (editorialEntity != null) {
            EditorialDto editorialDto = MapperEditorial.toDto(editorialEntity);

            return editorialDto;
        } else {
            throw new EditorialNoEncontradaException("No se encontró la editorial " + nombre + ".");
        }
    }

    @Override
    public String updateEditorial(Long id, EditorialDto editorialDto) {
        try {
            Editorial editorial = editorialRepository.findById(id).orElseThrow(() -> new
                                  EditorialNoEncontradaException("No se encontró la editorial con ID " + id + "."));

            editorial.setNombre(editorialDto.getNombre());
            editorial.setAlta(editorialDto.getAlta());

            editorialRepository.save(editorial);

            return "La editorial " + editorial.getNombre() + " fue actualizada correctamente.";
        } catch (EditorialNoEncontradaException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error en la actualización de la editorial: " + e.getMessage();
        }
    }

    @Override
    public String lowEditorial(Long id) {
        try {
            Editorial editorial = editorialRepository.findById(id).orElseThrow(() -> new
                                  EditorialNoEncontradaException("No se encontró la editorial con ID " + id + "."));

            editorial.setAlta(false);
            editorialRepository.save(editorial);

            return "La editorial con ID " + id + " fue dada de baja correctamente.";
        } catch (EditorialNoEncontradaException e) {
            return "La editorial con ID " + id + " no fue encontrada.";
        } catch (Exception e) {
            return "Error al dar de baja a la editorial con ID " + id + ": " + e.getMessage();
        }
    }

    @Override
    public String deleteEditorial(Long id) {
        try {
            editorialRepository.findById(id).orElseThrow(() -> new
                                EditorialNoEncontradaException("No se encontró la editorial con ID " + id + "."));

            editorialRepository.deleteById(id);

            return "La editorial con ID " + id + " fue eliminada correctamente.";
        } catch (EditorialNoEncontradaException e) {
            return "La editorial con ID " + id + " no fue encontrada.";
        } catch (Exception e) {
            return "Error en la eliminación de la editorial con ID " + id + ": " + e.getMessage();
        }
    }
}