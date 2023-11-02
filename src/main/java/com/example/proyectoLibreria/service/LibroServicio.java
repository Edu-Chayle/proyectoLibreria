package com.example.proyectoLibreria.service;

import com.example.proyectoLibreria.dto.LibroDto;
import com.example.proyectoLibreria.exceptions.LibroNoEncontradoException;
import com.example.proyectoLibreria.mapper.MapperLibro;
import com.example.proyectoLibreria.model.Libro;
import com.example.proyectoLibreria.repository.LibroRepositorio;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class LibroServicio implements LibroI {
    private final LibroRepositorio libroRepository;

    @Override
    public List<LibroDto> findAllLibros() {
        List<LibroDto> listaLibros = libroRepository.findAll().stream().map(MapperLibro::toDto)
                                                                       .collect(Collectors.toList());

        return listaLibros;
    }

    @Override
    public String saveLibro(LibroDto libroDto) {
        Libro libroEntity = MapperLibro.toEntity(libroDto);

        libroRepository.save(libroEntity);

        return "Libro guardado exitosamente.";
    }

    @Override
    public LibroDto findLibro(String titulo) {
        Libro libroEntity = libroRepository.findByTitulo(titulo);

        if (libroEntity != null) {
            LibroDto libroDto = MapperLibro.toDto(libroEntity);

            return libroDto;
        } else {
            throw new LibroNoEncontradoException("No se encontró el libro " + titulo + ".");
        }
    }

    @Override
    public String updateLibro(Long id, LibroDto libroDto) {
        try {
            Libro libro = libroRepository.findById(id).orElseThrow(() -> new
                          LibroNoEncontradoException("No se encontró el libro con ID " + id + "."));

            libro.setIsbn(libroDto.getIsbn());
            libro.setTitulo(libroDto.getTitulo());
            libro.setAnio(libroDto.getAnio());
            libro.setEjemplares(libroDto.getEjemplares());
            libro.setEjemplaresPrestados(libroDto.getEjemplaresPrestados());
            libro.setEjemplaresRestantes(libroDto.getEjemplaresRestantes());
            libro.setAlta(libroDto.getAlta());
            libro.setAutor(libroDto.getAutor());
            libro.setEditorial(libroDto.getEditorial());

            libroRepository.save(libro);

            return "El libro " + libro.getTitulo() + " fue actualizado correctamente.";
        } catch (LibroNoEncontradoException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Error en la actualización del libro: " + e.getMessage();
        }
    }

    @Override
    public String lowLibro(Long id) {
        try {
            Libro libro = libroRepository.findById(id).orElseThrow(() -> new
                          LibroNoEncontradoException("No se encontró el libro con ID " + id + "."));

            libro.setAlta(false);
            libroRepository.save(libro);

            return "El libro con ID " + id + " fue dado de baja correctamente.";
        } catch (LibroNoEncontradoException e) {
            return "El libro con ID " + id + " no fue encontrado.";
        } catch (Exception e) {
            return "Error al dar de baja el libro con ID " + id + ": " + e.getMessage();
        }
    }

    @Override
    public String deleteLibro(Long id) {
        try {
            libroRepository.findById(id).orElseThrow(() -> new
                            LibroNoEncontradoException("No se encontró el libro con ID " + id + "."));

            libroRepository.deleteById(id);

            return "El libro con ID " + id + " fue eliminado correctamente.";
        } catch (LibroNoEncontradoException e) {
            return "El libro con ID " + id + " no fue encontrado.";
        } catch (Exception e) {
            return "Error en la eliminación del libro con ID " + id + ": " + e.getMessage();
        }
    }
}