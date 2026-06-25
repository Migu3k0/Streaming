package com.example.ms_series.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_series.model.Serie;
import com.example.ms_series.service.SerieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/series")

public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener todas los series", description = "Obtiene una lista de los series")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Series obtenidas exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Serie.class))),
            @ApiResponse(responseCode = "404", description = "Series no encontradas"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Map<String, Object>> obtenerSerieCompleta(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findSerieCompleta(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las series", description = "Obtiene una lista de las series")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Series obtenidas exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Serie.class))),
            @ApiResponse(responseCode = "404", description = "Series no encontradas"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Serie> obtenerSerie() {
        return service.findAll();
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie creada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Serie.class))),
            @ApiResponse(responseCode = "404", description = "Serie no creada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    @Operation(summary = "Crear serie", description = "Crea una serie especificando sus propias caracteristicas")    
    public ResponseEntity<Serie> crear(@RequestBody Serie ser) {
        Serie nuevaSerie = service.saveSerie(ser);
        return new ResponseEntity<>(nuevaSerie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar series mediante IDs", description = "Elimina la serie introduciendo su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie eliminada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Serie.class))),
            @ApiResponse(responseCode = "404", description = "Serie no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public String eliminarSerie(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una serie", description = "Actualiza la serie mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie actualizada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Serie.class))),
            @ApiResponse(responseCode = "404", description = "Serie no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public Serie actualizarSerie(@PathVariable Integer id, @RequestBody Serie ser) {

        return service.update(id, ser);
    }
}
