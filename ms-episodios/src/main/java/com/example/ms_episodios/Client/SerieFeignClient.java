package com.example.ms_episodios.Client;

import com.example.ms_episodios.model.DTO.SerieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro servicio
@FeignClient(name = "ms-series")

public interface SerieFeignClient {

    @GetMapping("/api/v1/series/{id}")
    SerieDTO obtenerSeriePorId(@PathVariable("id") Integer id);

}
