package com.example.ms_episodios.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_episodios.model.Episodio;
import com.example.ms_episodios.service.EpisodioService;


import java.util.Map;


@RestController
@RequestMapping("/api/v1/episodios")

public class EpisodioController {

    @Autowired
    private EpisodioService service;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerEpisodioCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findEpisodioCompleto(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public List<Episodio> obtenerEpisodio() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Episodio> crear(@RequestBody Episodio ep) {
        Episodio nuevoEpisodio = service.saveEpisodio(ep);
        return new ResponseEntity<>(nuevoEpisodio, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarEpisodio(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Episodio actualizarEpisodio(@PathVariable Integer id, @RequestBody Episodio ep) {

        return service.update(id, ep);
    }
    
}
