package com.example.ms_perfil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ms_perfil.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}

