package com.example.ms_series.Client;

import com.example.ms_series.model.DTO.CategoriaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro servicio
@FeignClient(name = "ms-categorias", url = "localhost:8095")

public interface CategoriaFeignClient {

    @GetMapping("/api/v1/categorias/{id}")
    CategoriaDTO obtenerCategoriaPorId(@PathVariable("id") Integer id);

}
