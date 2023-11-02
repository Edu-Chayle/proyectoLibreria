package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.EditorialDto;
import java.util.List;

public interface EditorialI {
    List<EditorialDto> findAllEditoriales();
    String saveEditorial(EditorialDto editorialDto);
    EditorialDto findEditorial(String nombre);
    String updateEditorial(Long id, EditorialDto editorialDto);
    String lowEditorial(Long id);
    String deleteEditorial(Long id);
}