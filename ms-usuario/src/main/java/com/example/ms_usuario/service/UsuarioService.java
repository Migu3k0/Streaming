package com.example.ms_usuario.service;

import com.example.ms_usuario.model.Usuario;
import com.example.ms_usuario.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import java.util.Optional;
    
@Slf4j
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        log.debug("Consultando la base de datos para obtener todos los usuarios");
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        log.debug("Consultando la base de datos para el usuario con ID: {}", id);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            log.debug("Usuario con ID {} encontrado. Recuperando perfiles asociados vía FeignClient...", id);
            try {
                log.info("Comunicación con ms-perfil exitosa para el usuario {}", id);
            } catch (Exception e) {
                log.error("Error de comunicación con PerfilFeignClient para el usuario ID: {}", id, e);
            }
            return usuario;
        }

        log.warn("La búsqueda en base de datos no arrojó resultados para el ID: {}", id);
        return null;
    }

    public Usuario save(Usuario usuario) {
        log.debug("Iniciando guardado de usuario en base de datos...");
        Usuario savedUsuario = usuarioRepository.save(usuario);
        log.debug("Guardado exitoso en base de datos para el ID: {}", savedUsuario.getId());
        return savedUsuario;
    }

    public Usuario update(Integer id, Usuario usuarioActualizado) {
        log.debug("Buscando usuario ID: {} para actualizar", id);
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setNombre(usuarioActualizado.getNombre());

            log.debug("Aplicando cambios en base de datos para usuario ID: {}", id);
            return usuarioRepository.save(usuario);
        }

        log.warn("No se pudo aplicar la actualización. Usuario ID: {} no encontrado en BD", id);
        return null;
    }

    public boolean delete(Integer id) {
        log.debug("Verificando existencia del usuario ID: {} antes de eliminar", id);
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            log.debug("Borrado de la base de datos ejecutado para usuario ID: {}", id);
            return true;
        }

        log.warn("Operación abortada. El usuario ID: {} no existe para ser eliminado", id);
        return false;
    }
}