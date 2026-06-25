package com.example.ms_estudios.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_estudios.model.Estudio;
import com.example.ms_estudios.service.EstudioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/estudios")

public class EstudioController {

    @Autowired
    private EstudioService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estudios mediante IDs", description = "Obtiene el estudio con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudio obtenido exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Estudio.class))),
            @ApiResponse(responseCode = "404", description = "Estudio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    }) 
    public Estudio buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los estudios", description = "Obtiene una lista de los estudios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudios obtenidos exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Estudio.class))),
            @ApiResponse(responseCode = "404", description = "Estudios no encontrados"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Estudio> obtenerEstudio() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear estudio", description = "Crea un estudio especificando sus propias caracteristicas")  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudio creado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Estudio.class))),
            @ApiResponse(responseCode = "404", description = "Estudio no creado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Estudio> crear(@RequestBody Estudio est) {
        Estudio nuevoEstudio = service.saveEstudio(est);
        return new ResponseEntity<>(nuevoEstudio, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar estudios mediante IDs", description = "Elimina el estudio introduciendo su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudio eliminado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Estudio.class))),
            @ApiResponse(responseCode = "404", description = "Estudio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public String eliminarEstudio(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un estudio", description = "Actualiza el estudio mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudio actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Estudio.class))),
            @ApiResponse(responseCode = "404", description = "Estudio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public Estudio actualizarEstudio(@PathVariable Integer id, @RequestBody Estudio est) {

        return service.update(id, est);
    }
}
