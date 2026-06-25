package com.example.ms_peliculas.Client;

import com.example.ms_peliculas.model.DTO.EstudioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-estudios")

public interface EstudioFeignClient {

    @GetMapping("/api/v1/estudios/{id}")
    EstudioDTO obtenerEstudioPorId(@PathVariable("id") Integer id);

}
