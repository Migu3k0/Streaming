package com.example.ms_perfil.service;

import com.example.ms_perfil.Client.*;
import com.example.ms_perfil.model.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ms_perfil.model.Perfil;
import com.example.ms_perfil.repository.PerfilRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PerfilService {
    
    @Autowired
    private PerfilRepository repository;

     @Autowired
    private UsuarioFeignClient usuarioClient;

    public Perfil findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Perfil savePerfil(Perfil per){
        return repository.save(per);
    }

    public List<Perfil> findAll() {
        return repository.findAll();
    }


    public String deleteById(Integer id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Perfil eliminado exitosamente";
        }
        return "Perfil no disponible";
    }

    public Perfil update(Integer id, Perfil updPerfil) {

        Perfil PerfilElegido = repository.findById(id).orElse(null);

        if (PerfilElegido != null) {

            PerfilElegido.setNombre(updPerfil.getNombre());

            return repository.save(PerfilElegido);
        }

        return null;
    }

    public Map<String, Object> findPerfilCompleto(Integer id) {
        Perfil perfil = repository.findById(id).orElse(null);
        Map<String, Object> respuesta = new HashMap<>();

        if (perfil != null) {

            List<UsuarioDTO> usuariosDetalle = perfil.getUsuariosIds().stream()
                    .map(perfilId -> usuarioClient.obtenerUsuarioPorId(perfilId))
                    .collect(Collectors.toList());

            respuesta.put("id", perfil.getId());
            respuesta.put("nombre", perfil.getNombre());
            respuesta.put("usuario", usuariosDetalle);
        }
        return respuesta;
    }

}
