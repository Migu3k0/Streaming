package com.example.ms_favoritos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_favoritos.model.Favorito;
import com.example.ms_favoritos.service.FavoritoService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/favoritos")

public class FavoritoController {

    @Autowired
    private FavoritoService service;


    @GetMapping
    public List<Favorito> obtenerFavorito() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Favorito> crear(@RequestBody Favorito fav) {
        Favorito nuevaFavorito = service.saveFavorito(fav);
        return new ResponseEntity<>(nuevaFavorito, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarFavorito(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerFavoritoCompleto(@PathVariable Integer id) {
        Map<String, Object> respuesta = service.findFavorito(id);
        
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(respuesta);
    }

}
