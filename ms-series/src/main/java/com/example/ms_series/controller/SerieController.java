package com.example.ms_series.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_series.model.Serie;
import com.example.ms_series.service.SerieService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/series")

public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerSerieCompleta(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findSerieCompleta(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public List<Serie> obtenerSerie() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Serie> crear(@RequestBody Serie ser) {
        Serie nuevaSerie = service.saveSerie(ser);
        return new ResponseEntity<>(nuevaSerie, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarSerie(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Serie actualizarSerie(@PathVariable Integer id, @RequestBody Serie ser) {

        return service.update(id, ser);
    }
}
