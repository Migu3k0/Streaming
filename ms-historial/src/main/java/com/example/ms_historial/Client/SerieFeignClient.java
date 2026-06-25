package com.example.ms_historial.Client;

import com.example.ms_historial.model.DTO.SerieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-series")

public interface SerieFeignClient {

    @GetMapping("/api/v1/series/{id}")
    SerieDTO obtenerSeriePorId(@PathVariable("id") Integer id);

}
