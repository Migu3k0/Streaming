package com.example.ms_pelicula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_pelicula.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
}

