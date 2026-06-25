package com.example.ms_peliculas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_peliculas.model.Pelicula;
import com.example.ms_peliculas.service.PeliculaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/peliculas")

public class PeliculaController {

    @Autowired
    private PeliculaService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener peliculas mediante IDs", description = "Obtiene la pelicula con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pelicula completo obtenida exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pelicula.class))),
            @ApiResponse(responseCode = "404", description = "Pelicula no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    }) 
    public ResponseEntity<Map<String, Object>> obtenerPeliculaCompleta(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findPeliculaCompleta(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    @Operation(summary = "Obtener todas los peliculas", description = "Obtiene una lista de los peliculas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peliculas obtenidas exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pelicula.class))),
            @ApiResponse(responseCode = "404", description = "Peliculas no encontradas"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Pelicula> obtenerPelicula() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear pelicula", description = "Crea una pelicula especificando sus propias caracteristicas")  
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pelicula creada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pelicula.class))),
            @ApiResponse(responseCode = "404", description = "Pelicula no creada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Pelicula> crear(@RequestBody Pelicula peli) {
        Pelicula nuevaPelicula = service.savePelicula(peli);
        return new ResponseEntity<>(nuevaPelicula, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar peliculas mediante IDs", description = "Elimina la pelicula introduciendo su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pelicula eliminada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pelicula.class))),
            @ApiResponse(responseCode = "404", description = "Pelicula no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public String eliminarPelicula(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una pelicula", description = "Actualiza la pelicula mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pelicula actualizada exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Pelicula.class))),
            @ApiResponse(responseCode = "404", description = "Pelicula no encontrada"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public Pelicula actualizarPelicula(@PathVariable Integer id, @RequestBody Pelicula peli) {

        return service.update(id, peli);
    }
}
