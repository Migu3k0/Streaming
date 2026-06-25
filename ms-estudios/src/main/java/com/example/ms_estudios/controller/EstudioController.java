package com.example.ms_estudios.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ms_estudios.model.Estudio;
import com.example.ms_estudios.service.EstudioService;

@RestController
@RequestMapping("/api/v1/estudios")

public class EstudioController {

    @Autowired
    private EstudioService service;

    @GetMapping("/{id}")
    public Estudio buscarPorId(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Estudio> obtenerEstudio() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Estudio> crear(@RequestBody Estudio est) {
        Estudio nuevoEstudio = service.saveEstudio(est);
        return new ResponseEntity<>(nuevoEstudio, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String eliminarEstudio(@PathVariable Integer id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    public Estudio actualizarEstudio(@PathVariable Integer id, @RequestBody Estudio est) {

        return service.update(id, est);
    }
}
