package com.example.ms_suscripciones.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_suscripciones.model.Suscripcion;
import com.example.ms_suscripciones.service.SuscripcionService;

@RestController
@RequestMapping("/api/v1/suscripciones")

public class SuscripcionController {

    @Autowired
    private SuscripcionService service;

    @GetMapping("/{id}")
    public Suscripcion buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Suscripcion> obtenerSuscripcion() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Suscripcion> crear(@RequestBody Suscripcion sub) {
        Suscripcion nuevaSuscripcion = service.saveSuscripcion(sub);
        return new ResponseEntity<>(nuevaSuscripcion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarSuscripcion(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Suscripcion actualizarSuscripcion(@PathVariable Integer id, @RequestBody Suscripcion sub) {

        return service.update(id, sub);
    }
}
