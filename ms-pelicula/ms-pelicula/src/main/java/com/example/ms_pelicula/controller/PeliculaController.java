package com.example.ms_pelicula.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_pelicula.model.Pelicula;
import com.example.ms_pelicula.service.PeliculaService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/peliculas")

public class PeliculaController {

    @Autowired
    private PeliculaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPeliculaCompleta(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findPeliculaCompleta(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public List<Pelicula> obtenerEscultura() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Pelicula> crear(@RequestBody Pelicula peli) {
        Pelicula nuevaPelicula = service.savePelicula(peli);
        return new ResponseEntity<>(nuevaPelicula, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarEscultura(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Pelicula actualizarPelicula(@PathVariable Integer id, @RequestBody Pelicula pelic) {

        return service.update(id, pelic);
    }
}
