package com.example.ms_peliculas.Client;

import com.example.ms_peliculas.model.DTO.CategoriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-categorias")

public interface CategoriaFeignClient {

    @GetMapping("/api/v1/categorias/{id}")
    CategoriaDTO obtenerCategoriaPorId(@PathVariable("id") Integer id);

}
