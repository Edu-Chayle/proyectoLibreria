package com.example.proyectoLibreria.controller;

import com.example.proyectoLibreria.dto.LibroDto;
import com.example.proyectoLibreria.exceptions.LibroNoEncontradoException;
import com.example.proyectoLibreria.service.LibroI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/libros")
@RequiredArgsConstructor
@RestController

public class LibroController {
    private final LibroI libroService;

    @GetMapping("/findAll")
    public ResponseEntity<List<LibroDto>> findAllLibros() {
        List<LibroDto> lista =  libroService.findAllLibros();

        return new ResponseEntity<> (lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLibros(@RequestBody LibroDto libroDto) {
        String mensaje = libroService.saveLibro(libroDto);

        return new ResponseEntity<> (mensaje, HttpStatus.CREATED);
    }

    @GetMapping("/find/{titulo}")
    public ResponseEntity<?> findLibro(@PathVariable String titulo) {
        try {
            LibroDto libroDto = libroService.findLibro(titulo);

            return new ResponseEntity<> (libroDto, HttpStatus.OK);
        } catch (LibroNoEncontradoException e) {
            return new ResponseEntity<> (e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLibro(@PathVariable Long id, @RequestBody LibroDto libroDto) {
        try {
            String mensaje = libroService.updateLibro(id, libroDto);

            return ResponseEntity.ok(mensaje);
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la actualización del libro: "
                                                                                + e.getMessage());
        }
    }

    @PutMapping("/low/{id}")
    public ResponseEntity<String> lowLibro(@PathVariable Long id) {
        try {
            String mensaje = libroService.lowLibro(id);

            return ResponseEntity.ok(mensaje);
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El libro con ID " + id + " no fue encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar de baja el libro con ID "
                                                                                + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Long id) {
        try {
            String mensaje = libroService.deleteLibro(id);

            return ResponseEntity.ok(mensaje);
        } catch (LibroNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El libro con ID " + id + " no fue encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error en la eliminación del libro con ID " + id + ": " + e.getMessage());
        }
    }
}