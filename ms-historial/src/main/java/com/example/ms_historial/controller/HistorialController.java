package com.example.ms_historial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_historial.model.Historial;
import com.example.ms_historial.service.HistorialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/historiales")

public class HistorialController {

    @Autowired
    private HistorialService service;


    @GetMapping
    @Operation(summary = "Obtener todos los historiales", description = "Obtiene una lista de los historiales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historiales obtenidos exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Historial.class))),
            @ApiResponse(responseCode = "404", description = "Historiales no encontrados"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Historial> obtenerHistorial() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear historial", description = "Crea un historial especificando sus propias caracteristicas")  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial creado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Historial.class))),
            @ApiResponse(responseCode = "404", description = "Historial no creado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Historial> crear(@RequestBody Historial hist) {
        Historial nuevoHistorial = service.saveHistorial(hist);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar historiales mediante IDs", description = "Elimina el historial introduciendo su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial eliminado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Historial.class))),
            @ApiResponse(responseCode = "404", description = "Historial no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public String eliminarHistorial(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener historiales mediante IDs", description = "Obtiene el historial con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial completo obtenido exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Historial.class))),
            @ApiResponse(responseCode = "404", description = "Historial no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    }) 
    public ResponseEntity<Map<String, Object>> obtenerHistorialCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findHistorial(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

}
