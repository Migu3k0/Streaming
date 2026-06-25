package com.example.ms_series.Client;

import com.example.ms_series.model.DTO.EstudioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El name debe ser el spring.application.name del otro servicio
@FeignClient(name = "ms-estudios", url = "localhost:8093")

public interface EstudioFeignClient {

    @GetMapping("/api/v1/estudios/{id}")
    EstudioDTO obtenerEstudioPorId(@PathVariable("id") Integer id);

}
