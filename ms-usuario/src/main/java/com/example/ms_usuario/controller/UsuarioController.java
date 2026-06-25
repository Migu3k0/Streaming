package com.example.ms_usuario.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_usuario.model.Usuario;
import com.example.ms_usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con las usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        log.info("Petición REST recibida: Obtener todos los usuarios");
        List<Usuario> usuarios = usuarioService.findAll();
        log.debug("Se retornaron {} usuarios", usuarios.size());
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        log.info("Petición REST recibida: Obtener usuario con ID: {}", id);
        Usuario usuario = usuarioService.findById(id);
        
        if (usuario != null) {
            log.debug("Usuario encontrado correctamente con ID: {}", id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            log.warn("El usuario con ID: {} no fue encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        log.info("Petición REST recibida: Crear nuevo usuario");
        try {
            Usuario nuevoUsuario = usuarioService.save(usuario);
            log.info("Usuario creado exitosamente con ID: {}", nuevoUsuario.getId());
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error inesperado al crear el usuario", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        log.info("Petición REST recibida: Actualizar usuario con ID: {}", id);
        try {
            Usuario usuarioActualizado = usuarioService.update(id, usuario);
            if (usuarioActualizado != null) {
                log.info("Usuario con ID: {} actualizado exitosamente", id);
                return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
            } else {
                log.warn("No se pudo actualizar porque el usuario con ID: {} no existe", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al actualizar el usuario con ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        log.info("Petición REST recibida: Eliminar usuario con ID: {}", id);
        try {
            boolean eliminado = usuarioService.delete(id);
            if (eliminado) {
                log.info("Usuario con ID: {} eliminado exitosamente", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                log.warn("No se pudo eliminar porque el usuario con ID: {} no existe", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al eliminar el usuario con ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}