package com.example.ms_suscripciones.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_suscripciones.model.Suscripcion;
import com.example.ms_suscripciones.service.SuscripcionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/suscripciones")

public class SuscripcionController {

    @Autowired
    private SuscripcionService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener suscripciones mediante IDs", description = "Obtiene la suscripcion con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suscripcion obtenida exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Suscripcion.class))),
            @ApiResponse(responseCode = "404", description = "Suscripcion no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    }) 
    public Suscripcion buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las suscripciones", description = "Obtiene una lista de las suscripciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suscripciones obtenidas exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Suscripcion.class))),
            @ApiResponse(responseCode = "404", description = "Suscripciones no encontradas"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Suscripcion> obtenerSuscripcion() {
        return service.findAll();
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suscripcion creada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Suscripcion.class))),
            @ApiResponse(responseCode = "404", description = "Suscripcion no creada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Suscripcion> crear(@RequestBody Suscripcion sub) {
        Suscripcion nuevaSuscripcion = service.saveSuscripcion(sub);
        return new ResponseEntity<>(nuevaSuscripcion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar suscripciones mediante IDs", description = "Elimina la suscripcion introduciendo su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suscripcion eliminada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Suscripcion.class))),
            @ApiResponse(responseCode = "404", description = "Suscripcion no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public String eliminarSuscripcion(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una suscripcion", description = "Actualiza la suscripcion mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Suscripcion actualizada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Suscripcion.class))),
            @ApiResponse(responseCode = "404", description = "Suscripcion no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })

    public Suscripcion actualizarSuscripcion(@PathVariable Integer id, @RequestBody Suscripcion sub) {

        return service.update(id, sub);
    }
}
