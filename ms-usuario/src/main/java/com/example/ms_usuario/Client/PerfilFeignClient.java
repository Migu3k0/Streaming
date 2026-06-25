package com.example.ms_usuario.Client;

import com.example.ms_usuario.model.DTO.PerfilDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-perfil")

public interface PerfilFeignClient {

    @GetMapping("/api/v1/perfiles/{id}")
    PerfilDTO obtenerPerfilPorId(@PathVariable("id") Integer id);

}
