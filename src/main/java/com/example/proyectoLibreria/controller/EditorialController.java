package com.example.proyectoLibreria.controller;

import com.example.proyectoLibreria.dto.EditorialDto;
import com.example.proyectoLibreria.exceptions.EditorialNoEncontradaException;
import com.example.proyectoLibreria.service.EditorialI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/editoriales")
@RequiredArgsConstructor
@RestController

public class EditorialController {
    private final EditorialI editorialService;

    @GetMapping("/findAll")
    public ResponseEntity<List<EditorialDto>> findAllEditoriales() {
        List<EditorialDto> lista = editorialService.findAllEditoriales();

        return new ResponseEntity<> (lista, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEditorial(@RequestBody EditorialDto editorialDto) {
        String mensaje = editorialService.saveEditorial(editorialDto);

        return new ResponseEntity<> (mensaje, HttpStatus.CREATED);
    }

    @GetMapping("/find/{nombre}")
    public ResponseEntity<?> findEditorial(@PathVariable String nombre) {
        try {
            EditorialDto editorialDto = editorialService.findEditorial(nombre);

            return new ResponseEntity<> (editorialDto, HttpStatus.OK);
        } catch (EditorialNoEncontradaException e) {
            return new ResponseEntity<> (e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEditorial(@PathVariable Long id, @RequestBody EditorialDto editorialDto) {
        try {
            String mensaje = editorialService.updateEditorial(id, editorialDto);

            return ResponseEntity.ok(mensaje);
        } catch (EditorialNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error en la actualización de la editorial: " + e.getMessage());
        }
    }

    @PutMapping("/low/{id}")
    public ResponseEntity<String> lowEditorial(@PathVariable Long id) {
        try {
            String mensaje = editorialService.lowEditorial(id);

            return ResponseEntity.ok(mensaje);
        } catch (EditorialNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La editorial con ID " + id +
                                                                    " no fue encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al dar de baja a la editorial con ID " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEditorial(@PathVariable Long id) {
        try {
            String mensaje = editorialService.deleteEditorial(id);

            return ResponseEntity.ok(mensaje);
        } catch (EditorialNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La editorial con ID " + id +
                                                                    " no fue encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error en la eliminación de la editorial con ID " + id + ": " + e.getMessage());
        }
    }
}