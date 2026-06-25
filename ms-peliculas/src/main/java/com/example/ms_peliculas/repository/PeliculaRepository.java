package com.example.ms_peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_peliculas.model.Pelicula;;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
}

