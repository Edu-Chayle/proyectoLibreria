package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.AutorDto;
import com.example.proyectoLibreria.exceptions.AutorNoEncontradoException;
import com.example.proyectoLibreria.mapper.MapperAutor;
import com.example.proyectoLibreria.model.Autor;
import com.example.proyectoLibreria.repository.AutorRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class AutorServicio implements AutorI {
    private final AutorRepositorio autorRepository;

    @Override
    public List<AutorDto> findAllAutores() {
        List<AutorDto> listaAutores = autorRepository.findAll().stream().map(MapperAutor::toDto)
                                                                        .collect(Collectors.toList());

        return listaAutores;
    }

    @Override
    public String saveAutor(AutorDto autorDto) {
        Autor autorEntity = MapperAutor.toEntity(autorDto);

        autorRepository.save(autorEntity);

        return "Autor guardado exitosamente";
    }

    @Override
    public AutorDto findAutor(String nombre) {
        Autor autorEntity = autorRepository.findByNombre(nombre);

        if (autorEntity != null) {
            AutorDto autorDto = MapperAutor.toDto(autorEntity);

            return autorDto;
        } else {
            throw new AutorNoEncontradoException("No se encontró al autor " + nombre + ".");
        }
    }

    @Override
    public String updateAutor(Long id, AutorDto autorDto) {
        try {
            Autor autor = autorRepository.findById(id).orElseThrow(() -> new
                          AutorNoEncontradoException("No se encontró al autor con ID " + id + "."));

            autor.setNombre(autorDto.getNombre());
            autor.setAlta(autorDto.getAlta());

            autorRepository.save(autor);

            return "El autor " + autor.getNombre() + " fue actualizado correctamente.";
        } catch (AutorNoEncontradoException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error en la actualización del autor: " + e.getMessage();
        }
    }

    @Override
    public String lowAutor(Long id) {
        try {
            Autor autor = autorRepository.findById(id).orElseThrow(() -> new
                          AutorNoEncontradoException("No se encontró al autor con ID " + id + "."));

            autor.setAlta(false);
            autorRepository.save(autor);

            return "El autor con ID " + id + " fue dado de baja correctamente.";
        } catch (AutorNoEncontradoException e) {
            return "El autor con ID " + id + " no fue encontrado.";
        } catch (Exception e) {
            return "Error al dar de baja al autor con ID " + id + ": " + e.getMessage();
        }
    }

    @Override
    public String deleteAutor(Long id) {
        try {
            autorRepository.findById(id).orElseThrow(() -> new
                            AutorNoEncontradoException("No se encontró al autor con ID " + id + "."));

            autorRepository.deleteById(id);

            return "El autor con ID " + id + " fue eliminado correctamente.";
        } catch (AutorNoEncontradoException e) {
            return "El autor con ID " + id + " no fue encontrado.";
        } catch (Exception e) {
            return "Error en la eliminación del autor con ID " + id + ": " + e.getMessage();
        }
    }
}