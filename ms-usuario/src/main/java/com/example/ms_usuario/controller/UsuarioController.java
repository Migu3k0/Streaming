package com.example.ms_usuario.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_usuario.model.Usuario;
import com.example.ms_usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con las usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuarios mediante IDs", description = "Obtiene el usuario con la ID introducida")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario completo obtenido exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Map<String, Object>> obtenerUsuarioCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findUsuarioCompleto(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuarios no encontrados"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public List<Usuario> obtenerUsuario() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear usuario", description = "Crea un usuario especificando sus propias caracteristicas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no creado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public ResponseEntity<Usuario> crear(@RequestBody Usuario us) {
        Usuario nuevoUsuario = service.saveUsuario(us);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuarios mediante IDs", description = "Elimina el usuario introduciendo su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public String eliminarUsuario(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un usuario", description = "Actualiza el usuario mediante su ID y cambia caracteristicas")    
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Sintaxis invalida, compruebe que la solicitud este bien estructurada")
    })
    public Usuario actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario cate) {


        return service.update(id, cate);
    }
}
