package com.example.ms_categorias.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.stream.Collectors;

import com.example.ms_categorias.assemblers.CategoriaModelAssembler;
import com.example.ms_categorias.model.Categoria;
import com.example.ms_categorias.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorias")
public class CategoriaControllerV2 {

    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener todas las categorias", description = "Obtiene una lista de las categorias")
    public CollectionModel<EntityModel<Categoria>> obtenerCategorias() {
        List<EntityModel<Categoria>> categorias = service.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(categorias, linkTo(methodOn(CategoriaControllerV2.class).obtenerCategorias()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener categorias mediante IDs", description = "Obtiene la categoria con la ID introducida")    
    public EntityModel<Categoria> buscarPorId(@PathVariable Integer id) {
        Categoria categoria = service.findById(id);
        return assembler.toModel(categoria);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear categoria", description = "Crea una categoria especificando sus propias caracteristicas")    
    public ResponseEntity<EntityModel<Categoria>> createCategoria(@RequestBody Categoria categoria) {
        Categoria newCategoria = service.saveCategoria(categoria);
        return ResponseEntity
                .created(linkTo(methodOn(CategoriaControllerV2.class)
                .buscarPorId(newCategoria.getId()))
                .toUri()).body(assembler.toModel(newCategoria));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualiza una categoria", description = "Actualiza la categoria mediante su ID y cambia caracteristicas")    
    public ResponseEntity<EntityModel<Categoria>> updCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria updCategoria = service.saveCategoria(categoria);
        return ResponseEntity
                .ok(assembler.toModel(updCategoria));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar categorias mediante IDs", description = "Elimina la categoria introduciendo su ID")    
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}