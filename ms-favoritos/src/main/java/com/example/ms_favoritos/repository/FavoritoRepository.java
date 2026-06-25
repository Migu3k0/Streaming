package com.example.ms_favoritos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_favoritos.model.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
}

