package com.example.ms_series.Client;

import com.example.ms_series.model.DTO.EpisodioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro servicio
@FeignClient(name = "ms-episodios", url = "localhost:8094")

public interface EpisodioFeignClient {

    @GetMapping("/api/v1/episodios/{id}")
    EpisodioDTO obtenerEpisodioPorId(@PathVariable("id") Integer id);

}
