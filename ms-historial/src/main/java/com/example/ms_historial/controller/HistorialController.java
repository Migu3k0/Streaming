package com.example.ms_historial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_historial.model.Historial;
import com.example.ms_historial.service.HistorialService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/historiales")

public class HistorialController {

    @Autowired
    private HistorialService service;


    @GetMapping
    public List<Historial> obtenerHistorial() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Historial> crear(@RequestBody Historial hist) {
        Historial nuevoHistorial = service.saveHistorial(hist);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarHistorial(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerHistorialCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findHistorial(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

}
