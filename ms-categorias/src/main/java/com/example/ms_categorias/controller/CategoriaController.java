package com.example.ms_categorias.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_categorias.model.Categoria;
import com.example.ms_categorias.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categorias mediante IDs", description = "Obtiene la categoria con la ID introducida")    
    public Categoria buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las categorias", description = "Obtiene una lista de las categorias")
    public List<Categoria> obtenerCategoria() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear categoria", description = "Crea una categoria especificando sus propias caracteristicas")    
    public ResponseEntity<Categoria> crear(@RequestBody Categoria cate) {
        Categoria nuevaCategoria = service.saveCategoria(cate);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categorias mediante IDs", description = "Elimina la categoria introduciendo su ID")    
    public String eliminarCategoria(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una categoria", description = "Actualiza la categoria mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria actualizada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public Categoria actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria cate) {


        return service.update(id, cate);
    }
}
