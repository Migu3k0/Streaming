package com.example.ms_episodios.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_episodios.model.Episodio;
import com.example.ms_episodios.service.EpisodioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/episodios")

public class EpisodioController {

    @Autowired
    private EpisodioService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener episodios mediante IDs", description = "Obtiene el episodio con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Episodio completo obtenido exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Episodio.class))),
            @ApiResponse(responseCode = "404", description = "Episodio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    }) 
    public ResponseEntity<Map<String, Object>> obtenerEpisodioCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findEpisodioCompleto(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los episodios", description = "Obtiene una lista de los episodios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Episodios obtenidos exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Episodio.class))),
            @ApiResponse(responseCode = "404", description = "Episodios no encontrados"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Episodio> obtenerEpisodio() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear episodio", description = "Crea un episodio especificando sus propias caracteristicas")  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Episodio creado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Episodio.class))),
            @ApiResponse(responseCode = "404", description = "Episodio no creado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Episodio> crear(@RequestBody Episodio ep) {
        Episodio nuevoEpisodio = service.saveEpisodio(ep);
        return new ResponseEntity<>(nuevoEpisodio, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar episodios mediante IDs", description = "Elimina el episodio introduciendo su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Episodio eliminado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Episodio.class))),
            @ApiResponse(responseCode = "404", description = "Episodio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public String eliminarEpisodio(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un episodio", description = "Actualiza el episodio mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Episodio actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Episodio.class))),
            @ApiResponse(responseCode = "404", description = "Episodio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public Episodio actualizarEpisodio(@PathVariable Integer id, @RequestBody Episodio ep) {

        return service.update(id, ep);
    }
    
}
