package com.example.ms_usuario.service;

import com.example.ms_usuario.Client.*;
import com.example.ms_usuario.model.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_usuario.Client.PerfilFeignClient;
import com.example.ms_usuario.model.Usuario;
import com.example.ms_usuario.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Map;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilFeignClient perfilClient;

    public Usuario findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario us){
        return repository.save(us);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Usuario eliminado exitosamente";
        }
        return "Usuario no disponible";
    }

    public Usuario update(Integer id, Usuario updUsuario) {

        Usuario UsuarioElegido = repository.findById(id).orElse(null);

        if (UsuarioElegido != null) {

            UsuarioElegido.setNombre(updUsuario.getNombre());
            UsuarioElegido.setEmail(updUsuario.getEmail());
            UsuarioElegido.setContrasenia(updUsuario.getContrasenia());

            return repository.save(UsuarioElegido);
        }

        return null;
    }

    public Map<String, Object> findUsuarioCompleto(Integer id) {
        Usuario usuario = repository.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if (usuario != null) {

            List<PerfilDTO> perfilesDetalle = usuario.getPerfilesIds().stream()
                    .map(usuarioId -> perfilClient.obtenerPerfilPorId(usuarioId))
                    .collect(Collectors.toList());

            respuesta.put("id", usuario.getId());
            respuesta.put("nombre", usuario.getNombre());
            respuesta.put("perfiles", perfilesDetalle);
        }
        return respuesta;
    }


}
