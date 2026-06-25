package com.example.ms_historial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_historial.model.Historial;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {
}

