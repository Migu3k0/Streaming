package com.example.ms_favoritos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_favoritos.model.Favorito;
import com.example.ms_favoritos.service.FavoritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/favoritos")

public class FavoritoController {

    @Autowired
    private FavoritoService service;


    @GetMapping
    @Operation(summary = "Obtener todos los favoritos", description = "Obtiene una lista de los favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favoritos obtenidos exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Favorito.class))),
            @ApiResponse(responseCode = "404", description = "Favoritos no encontrados"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Favorito> obtenerFavorito() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear favorito", description = "Crea un favorito especificando sus propias caracteristicas")  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorito creado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Favorito.class))),
            @ApiResponse(responseCode = "404", description = "Favorito no creado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Favorito> crear(@RequestBody Favorito fav) {
        Favorito nuevaFavorito = service.saveFavorito(fav);
        return new ResponseEntity<>(nuevaFavorito, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarFavorito(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener favoritos mediante IDs", description = "Obtiene el favorito con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorito completo obtenido exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Favorito.class))),
            @ApiResponse(responseCode = "404", description = "Favorito no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    }) 
    public ResponseEntity<Map<String, Object>> obtenerFavoritoCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findFavorito(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

}
