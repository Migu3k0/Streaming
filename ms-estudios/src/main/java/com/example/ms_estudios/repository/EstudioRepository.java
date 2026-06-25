package com.example.ms_estudios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_estudios.model.Estudio;

@Repository
public interface EstudioRepository extends JpaRepository<Estudio, Integer> {
}

