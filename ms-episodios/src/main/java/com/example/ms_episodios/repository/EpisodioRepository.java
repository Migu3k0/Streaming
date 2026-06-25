package com.example.ms_episodios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_episodios.model.Episodio;

@Repository
public interface EpisodioRepository extends JpaRepository<Episodio, Integer> {
}

