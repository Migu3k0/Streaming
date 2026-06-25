package com.example.ms_perfil.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_perfil.model.Perfil;
import com.example.ms_perfil.service.PerfilService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/perfiles")
@Tag(name = "Perfiles", description = "Operaciones relacionadas con las perfiles")
public class PerfilController {

    @Autowired
    private PerfilService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener perfiles mediante IDs", description = "Obtiene el perfil con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil completo obtenido exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Perfil.class))),
            @ApiResponse(responseCode = "404", description = "Perfil no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    }) 
    public ResponseEntity<Map<String, Object>> obtenerPerfilCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findPerfilCompleto(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los perfiles", description = "Obtiene una lista de los perfiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfiles obtenidos exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Perfil.class))),
            @ApiResponse(responseCode = "404", description = "Perfiles no encontrados"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Perfil> obtenerPerfil() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear perfil", description = "Crea un perfil especificando sus propias caracteristicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil creado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Perfil.class))),
            @ApiResponse(responseCode = "404", description = "Perfil no creado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })    
    public ResponseEntity<Perfil> crear(@RequestBody Perfil us) {
        Perfil nuevoPerfil = service.savePerfil(us);
        return new ResponseEntity<>(nuevoPerfil, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil eliminado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Perfil.class))),
            @ApiResponse(responseCode = "404", description = "Perfil no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    @Operation(summary = "Eliminar perfiles mediante IDs", description = "Elimina el perfil introduciendo su ID")    
    public String eliminarPerfil(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un perfil", description = "Actualiza el perfil mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Perfil.class))),
            @ApiResponse(responseCode = "404", description = "Perfil no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public Perfil actualizarPerfil(@PathVariable Integer id, @RequestBody Perfil per) {


        return service.update(id, per);
    }
}
